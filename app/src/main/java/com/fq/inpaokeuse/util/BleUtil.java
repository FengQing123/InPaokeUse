package com.fq.inpaokeuse.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengqing
 * @date 2018/10/17
 */

public class BleUtil {

    private static final String TAG = "BleUtil";
    private static BleUtil mBleUtil;
    private Context mContext;
    private BLEUTIListener mBleListener;
    private OTCUPListener mOtcUpListener;
    private boolean mStopUpdate = false;
    private Handler mHandler = new Handler();

    private int SCAN_PERIOD = 10000;
    private boolean isScaning = false;

    private BluetoothManager mBlueToothManager;
    private BluetoothAdapter mBlueToothAdapter;
    private BluetoothGatt mBlueToothGatt;
    private List<BluetoothDevice> mDeviceList = new ArrayList<>();


    private BluetoothGattCharacteristic write_characteristic = null;// 写特征
    private BluetoothGattCharacteristic read_characteristic = null;// 读特征
    private BluetoothGattService OXFFFO_SERVICE;
    private String OXFFFO_SERVICE_UUID = "0000fff0-0000-1000-8000-00805f9b34fb";
    private String READ_CHARACTERISTIC_UUID = "0000fff1-0000-1000-8000-00805f9b34fb";
    private String WRITE_CHARACTERISTIC_UUID = "0000fff2-0000-1000-8000-00805f9b34fb";

    public int DATA_SEDNTAG = 0x3d;
    public int DATA_GETTAG = 0x3c;

    public static synchronized BleUtil getInstance() {
        if (mBleUtil == null) {
            synchronized (BleUtil.class) {
                if (mBleUtil == null) {
                    mBleUtil = new BleUtil();
                }
            }
        }
        return mBleUtil;
    }

    public void setmBleListener(BLEUTIListener listener) {
        this.mBleListener = listener;
    }

    public void setmOtcUpListener(OTCUPListener listener) {
        this.mOtcUpListener = listener;
    }

    public void scanDevice(final boolean enable) {
        if (enable) {
            if (!isScaning) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopScan();
                    }
                }, SCAN_PERIOD);
                startScan();
            }
        } else {
            mHandler.removeCallbacksAndMessages(null);
            stopScan();
        }
    }

    public void connectDevice(String address, boolean enable) {
        if (mBlueToothAdapter == null || mContext == null) {
            return;
        }
        if (!enable) {
            mDeviceList.clear();
            disConnected();
            mBleListener.isConnected(false);
        } else {
            BluetoothDevice device = mBlueToothAdapter.getRemoteDevice(address);
            if (device != null) {
                scanDevice(false);
                mBlueToothGatt = device.connectGatt(mContext, false, mGattCallBack);
                mBleListener.onConnecting(device);
            }
        }
    }

    public boolean isSupport(Context mContext) {
        this.mContext = mContext;
        if (!mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            showToast("BLE不支持此设备!");
            return false;
        }
        mBlueToothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        if (mBlueToothManager != null) {
            mBlueToothAdapter = mBlueToothManager.getAdapter();
        }
        if (mBlueToothAdapter == null || !mBlueToothAdapter.isEnabled()) {
            showToast("请先打开蓝牙");
            return false;
        }
        return true;
    }

    public String generateDisplayMsg(String title, int elapsedTime, int byteRate) {
        return new String(title + "\n" + elapsedTime + " s" + "\n" + byteRate + " Bps");
    }


    /**
     * 发送数据
     */
    public void writeData(byte[] data) {
        int len = data.length;
        byte[] sendData = new byte[len + 3]; // 头+要发送的数据+校验码+尾，组合成发送字节
        sendData[0] = 2; // 发送数据的起始标志位
        int checkCode = 0; // 发送数据的校验码
        for (int i = 0; i < len; i++) {
            sendData[i + 1] = data[i];
            checkCode = (checkCode ^ data[i]);
        }
        sendData[len + 1] = (byte) checkCode;
        sendData[len + 2] = 3; // 发送数据的结束标志位

        if (write_characteristic != null && mBlueToothGatt != null) {
            write_characteristic.getWriteType();
            write_characteristic.setValue(sendData);
            write_characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
            boolean flag = mBlueToothGatt.writeCharacteristic(write_characteristic);
            Log.e(TAG, "是否发送成功=" + flag);
            if (!flag) {
                Toast.makeText(mContext, "设置失败", Toast.LENGTH_SHORT).show();
            }

            /**
             * 打印APP发送的数据（跑步机）
             */
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < sendData.length; i++) {
                str.append(Integer.toHexString(sendData[i]) + ",");
            }
            Log.e(TAG, "APP发送的数据=" + str.toString());
        } else {
            Toast.makeText(mContext, "设置失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void processData(byte[] data, int length) {
        if (data[0] == 2 && data[length - 1] == 3) {
            int checkCode = 0;
            for (int i = 1; i < length - 2; i++) {
                checkCode = checkCode ^ data[i];
            }
            if (checkCode == data[length - 2]) {
                if (data[2] == DATA_GETTAG) {
                    byte frequency = data[5];
                    Logger.error(TAG, "频率=" + frequency);
                } else if (data[2] == DATA_SEDNTAG) {
                }
                mBleListener.processData(data);
            }
        }
    }


    /**
     * 扫描回调
     */
    private final BluetoothAdapter.LeScanCallback mLeScanCallBack = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            String name = device.getName();
            Logger.error(TAG, "deviceName=" + name);
            if (name != null && !"".equals(name) && name.contains("FS")) {
                if (!mDeviceList.contains(device)) {
                    mDeviceList.add(device);
                    mBleListener.onLeScanDevices(mDeviceList);
                }
            }


        }
    };

    /**
     * 连接回调
     */
    private final BluetoothGattCallback mGattCallBack = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    // 如果连接上，调用发现服务方法
                    mBlueToothGatt.discoverServices();
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    // 未连接
                    mBleListener.isConnected(false);
                    gatt.disconnect();
                    gatt.close();
                }
            } else {
                Logger.error(TAG, "TreadMill: connectFail---");
                mBleListener.isConnected(false);
                gatt.disconnect();
                gatt.close();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            List<BluetoothGattService> BluetoothGattServices = mBlueToothGatt.getServices();
            for (BluetoothGattService service : BluetoothGattServices) {
                if (service.getUuid().toString().equals(OXFFFO_SERVICE_UUID)) {
                    OXFFFO_SERVICE = service;
                }
                List<BluetoothGattCharacteristic> chraclist = service.getCharacteristics();
                for (BluetoothGattCharacteristic c : chraclist) {
                    if (c.getUuid().toString().equals(READ_CHARACTERISTIC_UUID)) {
                        read_characteristic = c;
                    } else if (c.getUuid().toString().equals(WRITE_CHARACTERISTIC_UUID)) {
                        write_characteristic = c;
                    }
                }
            }

            // 监听读特征
            setCharacteristicNotification(read_characteristic, true);
            mBleListener.isConnected(true);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            byte[] notifyData = characteristic.getValue();
            /**
             * 打印跑步机返回的数据
             */
            byte[] aa = characteristic.getValue();
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < aa.length; i++) {
                str.append(Integer.toHexString(aa[i]) + ",");
            }
            Log.e(TAG, "跑步机返回数据=" + str.toString());

            processData(aa, aa.length);

        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {

            } else {
                mStopUpdate = true;
                String errCode = "Gatt write fail,errCode:" + String.valueOf(status);
                Message msg = new Message();
                msg.what = Constants.ERROR_CODE;
                msg.getData().putString("ERROR_CODE", errCode);
                mOtcUpListener.sendUpdateMsg(msg);
            }
        }
    };


    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void startScan() {
        if (mBlueToothAdapter != null) {
            mDeviceList.clear();
            isScaning = true;
            mBlueToothAdapter.startLeScan(mLeScanCallBack);
            mBleListener.onLeScanStart();
        }
    }

    private void stopScan() {
        if (mBlueToothAdapter != null) {
            isScaning = false;
            mBlueToothAdapter.stopLeScan(mLeScanCallBack);
            mBleListener.onLeScanStop();
        }
    }

    private void disConnected() {
        if (mBlueToothGatt != null) {
            mBlueToothGatt.disconnect();
            mBlueToothGatt.close();
            mBlueToothGatt = null;
        }
    }


    private void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBlueToothAdapter == null || mBlueToothGatt == null || characteristic == null) {
            return;
        }
        mBlueToothGatt.setCharacteristicNotification(characteristic, enabled);
    }

    public interface BLEUTIListener {
        void onLeScanStart();

        void onLeScanStop();

        void onLeScanDevices(List<BluetoothDevice> deviceList);

//        void onConnected(BluetoothDevice mCurrentDevice);
//
//        void onDisConnected(BluetoothDevice mCurrentDevice);

        void isConnected(boolean isConnected);

        void onConnecting(BluetoothDevice mCurrentDevice);

        void isSupportOta(boolean support);

        void processData(byte[] data);

//        void onDisConnecting(BluetoothDevice mCurrentDevice);//连接失败
    }

    public interface OTCUPListener {
        void showProgress(String title, String message);

        void sendUpdateMsg(Message msg);
    }


}
