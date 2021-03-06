package com.ltmonitor.jt809.protocol.send;

import com.ltmonitor.jt809.app.GlobalConfig;
import com.ltmonitor.jt809.model.JT809Message;
import com.ltmonitor.jt809.model.ParameterModel;
import com.ltmonitor.jt809.protocol.ISendProtocol;
import com.ltmonitor.jt809.tool.Tools;
import org.apache.log4j.Logger;

/**
 * 实时音视频请求应答
 */
public class UpRealVideoMsgEndAck implements ISendProtocol
{
    private static Logger logger = Logger.getLogger(UpCtrlMsg.class);

    private int msgType = 0x1800;
    private int subType = 0x1802;
    private String plateNo;
    private int plateColor;
    /**
     * 0:成功，1：失败，2：不支持，3：会话结束
     */
    private byte result;


    public UpRealVideoMsgEndAck(String plateNo, int plateColor, byte result) {
        this.plateNo = plateNo;
        this.plateColor = plateColor;
        this.result = result;
    }

    public JT809Message wrapper() {
        int dataLength = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(Tools.ToHexString(plateNo, 21))
                .append(Tools.ToHexString(plateColor,1))
                .append(Tools.ToHexString(subType, 2))
                .append(Tools.ToHexString(dataLength, 4))
                .append(Tools.ToHexString(result, 1));

        String body = sb.toString();

        JT809Message mm = new JT809Message(msgType, subType,body);
        mm.setPlateColor(plateColor);
        mm.setPlateNo(plateNo);
        return mm;
    }
}
