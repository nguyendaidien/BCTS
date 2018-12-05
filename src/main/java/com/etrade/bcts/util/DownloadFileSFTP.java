package com.etrade.bcts.util;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
public class DownloadFileSFTP {
	 
    public static void main(String[] args) throws Exception {
 
    	JSch jsch = new JSch();
        Session session = null;
        try {
           session = jsch.getSession("ajayasamanta@crimsonlogic.com", "172.20.21.27", 21);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("Pass_1122");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            String source="/bcts/OUTPMT_vp4t003_201110055504.xml";
            String dest="D:\\gets\\bcts\\permitIn\\20181129\\IPTPMT_vp4t003_201110065304.xml";
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get(source, dest);  
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
 
   }
 
}