/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZKFinger;

import DAO.HibernateDAO;
import Model.Identifications;
import Model.Inscriptions;
import Utils.ImageUtils;
import com.zkteco.biometric.FingerprintSensorErrorCode;
import com.zkteco.biometric.FingerprintSensorEx;
import java.awt.Component;
import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eqima
 */
public class ZKFinger {

    JButton btnImg;
    //the width of fingerprint image
    int fpWidth = 0;
    //the height of fingerprint image
    int fpHeight = 0;
    //for verify test
    private byte[] lastRegTemp = new byte[2048];
    //the length of lastRegTemp
    private int cbRegTemp = 0;
    //pre-register template
    private byte[][] regtemparray = new byte[3][2048];
    //Register
    private boolean bRegister = false;
    //Identify
    private boolean bIdentify = true;
    //finger id
    private int iFid = 1;

    private int nFakeFunOn = 1;
    //must be 3
    static final int enroll_cnt = 3;
    //the index of pre-register function
    private int enroll_idx = 0;

    private byte[] imgbuf = null;
    private byte[] template = new byte[2048];
    private int[] templateLen = new int[1];

    private boolean mbStop = true;
    private long mhDevice = 0;
    private long mhDB = 0;
    private ZKFinger.WorkThread workThread = null;

    public ZKFinger(JButton btnImg) {
        this.btnImg = btnImg;
    }

    public ZKFinger() {
        //To change body of generated methods, choose Tools | Templates.
    }

    private class WorkThread extends Thread {

        private JFrame frame;
        ZKFinger zk = new ZKFinger();

        private WorkThread(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void run() {

            super.run();
            int ret = 0;
            while (!mbStop) {
                templateLen[0] = 2048;
                if (0 == (ret = FingerprintSensorEx.AcquireFingerprint(mhDevice, imgbuf, template, templateLen))) {
                    OnCatpureOK(imgbuf, frame);

                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static byte[] changeByte(int data) {
        return intToByteArray(data);
    }

    public static void writeBitmap(byte[] imageBuf, int nWidth, int nHeight,
            String path) throws IOException {
        java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
        java.io.DataOutputStream dos = new java.io.DataOutputStream(fos);

        int w = (((nWidth + 3) / 4) * 4);
        int bfType = 0x424d; // 位图文件类型（0—1字节）
        int bfSize = 54 + 1024 + w * nHeight;// bmp文件的大小（2—5字节）
        int bfReserved1 = 0;// 位图文件保留字，必须为0（6-7字节）
        int bfReserved2 = 0;// 位图文件保留字，必须为0（8-9字节）
        int bfOffBits = 54 + 1024;// 文件头开始到位图实际数据之间的字节的偏移量（10-13字节）

        dos.writeShort(bfType); // 输入位图文件类型'BM'
        dos.write(changeByte(bfSize), 0, 4); // 输入位图文件大小
        dos.write(changeByte(bfReserved1), 0, 2);// 输入位图文件保留字
        dos.write(changeByte(bfReserved2), 0, 2);// 输入位图文件保留字
        dos.write(changeByte(bfOffBits), 0, 4);// 输入位图文件偏移量

        int biSize = 40;// 信息头所需的字节数（14-17字节）
        int biWidth = nWidth;// 位图的宽（18-21字节）
        int biHeight = nHeight;// 位图的高（22-25字节）
        int biPlanes = 1; // 目标设备的级别，必须是1（26-27字节）
        int biBitcount = 8;// 每个像素所需的位数（28-29字节），必须是1位（双色）、4位（16色）、8位（256色）或者24位（真彩色）之一。
        int biCompression = 0;// 位图压缩类型，必须是0（不压缩）（30-33字节）、1（BI_RLEB压缩类型）或2（BI_RLE4压缩类型）之一。
        int biSizeImage = w * nHeight;// 实际位图图像的大小，即整个实际绘制的图像大小（34-37字节）
        int biXPelsPerMeter = 0;// 位图水平分辨率，每米像素数（38-41字节）这个数是系统默认值
        int biYPelsPerMeter = 0;// 位图垂直分辨率，每米像素数（42-45字节）这个数是系统默认值
        int biClrUsed = 0;// 位图实际使用的颜色表中的颜色数（46-49字节），如果为0的话，说明全部使用了
        int biClrImportant = 0;// 位图显示过程中重要的颜色数(50-53字节)，如果为0的话，说明全部重要

        dos.write(changeByte(biSize), 0, 4);// 输入信息头数据的总字节数
        dos.write(changeByte(biWidth), 0, 4);// 输入位图的宽
        dos.write(changeByte(biHeight), 0, 4);// 输入位图的高
        dos.write(changeByte(biPlanes), 0, 2);// 输入位图的目标设备级别
        dos.write(changeByte(biBitcount), 0, 2);// 输入每个像素占据的字节数
        dos.write(changeByte(biCompression), 0, 4);// 输入位图的压缩类型
        dos.write(changeByte(biSizeImage), 0, 4);// 输入位图的实际大小
        dos.write(changeByte(biXPelsPerMeter), 0, 4);// 输入位图的水平分辨率
        dos.write(changeByte(biYPelsPerMeter), 0, 4);// 输入位图的垂直分辨率
        dos.write(changeByte(biClrUsed), 0, 4);// 输入位图使用的总颜色数
        dos.write(changeByte(biClrImportant), 0, 4);// 输入位图使用过程中重要的颜色数

        for (int i = 0; i < 256; i++) {
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(i);
            dos.writeByte(0);
        }

        byte[] filter = null;
        if (w > nWidth) {
            filter = new byte[w - nWidth];
        }

        for (int i = 0; i < nHeight; i++) {
            dos.write(imageBuf, (nHeight - 1 - i) * nWidth, nWidth);
            if (w > nWidth) {
                dos.write(filter, 0, w - nWidth);
            }
        }
        dos.flush();
        dos.close();
        fos.close();
    }

    private void OnCatpureOK(byte[] imgBuf, JFrame frame) {
        JTable table = null;
        JButton btnPhotos = null;
        try {
            writeBitmap(imgBuf, fpWidth, fpHeight, "fingerprint.bmp");
            btnImg.setIcon(new ImageIcon(ImageUtils.resizeImage(ImageIO.read(new File("fingerprint.bmp")), 200, 200)));

            Component[] components = frame.getContentPane().getComponents();
            for (Component component : components) {
                if (component instanceof JScrollPane) {
                    JScrollPane scrollPane = (JScrollPane) component;
                    Component[] scrollComponent = scrollPane.getViewport().getComponents();
                    for (Component component1 : scrollComponent) {
                        if (component1 instanceof JTable) {
                            table = (JTable) component1;
                        }
                    }
                }
                if (component instanceof JButton && ((JButton) component).getText().equals("Photos 4 X4")) {
                    btnPhotos = (JButton) component;
                    System.out.println(btnPhotos.getText());
                }
            }
            if (frame.getClass().getName().equals("Fenetre.Identification")) {
                Inscriptions ins = this.identification();
                HibernateDAO dao = new HibernateDAO();
                Identifications ident = new Identifications();
                if (ins == null) {
                    JOptionPane.showMessageDialog(frame, "identification inconnu");
                } else {
                    Identifications isIdentifier = (Identifications) dao.findAllByDateAjourdhui(ident, ins.getId());
//                System.out.println("existe====" + isIdentifier.getDate());
                    if (isIdentifier == null) {
                        ident = new Identifications(new Date(), new Date(), null, "0", ins.getId(), ins.getNom());
                        dao.save(ident);
                    } else {
                        System.out.println(isIdentifier.getDate());
                        LocalDateTime time1 = convertDateToLocalDateTime(isIdentifier.getHeureDebut());
                        LocalDateTime time2 = convertDateToLocalDateTime(new Date());
                        isIdentifier.setHeureFin(new Date());
                        isIdentifier.setHeureTotal(calculateDateTimeDifference(time1, time2));
                        dao.update(isIdentifier);
                    }
                }
                int i = 0;
                List<Identifications> list = dao.findAllByDateAjourdhui(new Identifications());

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("ID");
                model.addColumn("Nom");
                model.addColumn("Date");
                model.addColumn("Heure Début");
                model.addColumn("Heure Fin");
                model.addColumn("Heure Total");

// Ajout des données au modèle
                for (Identifications identifications : list) {
                    Object[] rowData = new Object[6];
                    rowData[0] = identifications.getIdInscription();
                    rowData[1] = identifications.getNom();
                    rowData[2] = identifications.getDate();
                    rowData[3] = (identifications.getHeureDebut() != null) ? String.valueOf(identifications.getHeureDebut()).split(" ")[1] : "0:0:0";
                    rowData[4] = (identifications.getHeureFin() != null) ? String.valueOf(identifications.getHeureFin()).split(" ")[1] : "0:0:0";
                    rowData[5] = (identifications.getHeureTotal().length() > 0) ? identifications.getHeureTotal() : "0:0:0";
                    model.addRow(rowData);
                }
                table.setModel(model);
                if (ins!=null) {
                    btnPhotos.setIcon(new ImageIcon(ImageUtils.convertByteArrayToImage(ins.getPhoto())));
                }
                
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String calculateDateTimeDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        Duration duration = Duration.between(dateTime1, dateTime2);
        int totalMinutes = (int) duration.toMinutes();
        int heures = totalMinutes / 60;
        int minutesRestantes = totalMinutes % 60;
        return String.valueOf(heures) + ":" + String.valueOf(minutesRestantes);
    }

    private LocalDateTime convertDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public void FreeSensor() {
        mbStop = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (0 != mhDB) {
            FingerprintSensorEx.DBFree(mhDB);
            mhDB = 0;
        }
        if (0 != mhDevice) {
            FingerprintSensorEx.CloseDevice(mhDevice);
            mhDevice = 0;
        }
        FingerprintSensorEx.Terminate();
    }

    public static byte[] intToByteArray(final int number) {
        byte[] abyte = new byte[4];
        // "&" 与（AND），对两个整型操作数中对应位执行布尔代数，两个位都为1时输出1，否则0。  
        abyte[0] = (byte) (0xff & number);
        // ">>"右移位，若为正数则高位补0，若为负数则高位补1  
        abyte[1] = (byte) ((0xff00 & number) >> 8);
        abyte[2] = (byte) ((0xff0000 & number) >> 16);
        abyte[3] = (byte) ((0xff000000 & number) >> 24);
        return abyte;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int number = bytes[0] & 0xFF;
        // "|="按位或赋值。  
        number |= ((bytes[1] << 8) & 0xFF00);
        number |= ((bytes[2] << 16) & 0xFF0000);
        number |= ((bytes[3] << 24) & 0xFF000000);
        return number;
    }

    public void openScanner(JFrame frame) {

        // TODO Auto-generated method stub
        if (0 != mhDevice) {
            //already inited
            System.out.println("Please close device first!");
            return;
        }
        int ret = FingerprintSensorErrorCode.ZKFP_ERR_OK;
        //Initialize
        cbRegTemp = 0;
        bRegister = false;
        bIdentify = false;
        iFid = 1;
        enroll_idx = 0;
        if (FingerprintSensorErrorCode.ZKFP_ERR_OK != FingerprintSensorEx.Init()) {
            System.out.println("Init failed!");
            return;
        }
        ret = FingerprintSensorEx.GetDeviceCount();
        if (ret < 0) {
            System.out.println("No devices connected!");
            FreeSensor();
            return;
        }
        if (0 == (mhDevice = FingerprintSensorEx.OpenDevice(0))) {
            System.out.println("Open device fail, ret = " + ret + "!");
            FreeSensor();
            return;
        }
        if (0 == (mhDB = FingerprintSensorEx.DBInit())) {
            System.out.println("Init DB fail, ret = " + ret + "!");
            FreeSensor();
            return;
        }

        FingerprintSensorEx.DBSetParameter(mhDB, 5010, 1);
        //For ISO/Ansi End

        //set fakefun off
        //FingerprintSensorEx.SetParameter(mhDevice, 2002, changeByte(nFakeFunOn), 4);
        byte[] paramValue = new byte[4];
        int[] size = new int[1];
        //GetFakeOn
        //size[0] = 4;
        //FingerprintSensorEx.GetParameters(mhDevice, 2002, paramValue, size);
        //nFakeFunOn = byteArrayToInt(paramValue);

        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 1, paramValue, size);
        fpWidth = byteArrayToInt(paramValue);
        size[0] = 4;
        FingerprintSensorEx.GetParameters(mhDevice, 2, paramValue, size);
        fpHeight = byteArrayToInt(paramValue);
        //width = fingerprintSensor.getImageWidth();
        //height = fingerprintSensor.getImageHeight();
        imgbuf = new byte[fpWidth * fpHeight];
//        btnImg.resize(fpWidth, fpHeight);
        mbStop = false;
        workThread = new ZKFinger.WorkThread(frame);
        workThread.start();// 线程启动
        System.out.println("Open succ!");
    }

    public int save(Inscriptions ins) {
        HibernateDAO dao = new HibernateDAO();
        byte[] fpTemplate = new byte[2048];
        int[] sizeFPTemp = new int[1];
        sizeFPTemp[0] = 2048;
        FingerprintSensorEx.ExtractFromImage(mhDB, "fingerprint.bmp", 500, fpTemplate, sizeFPTemp);
        ins.setEmpreinte(template);
        dao.save(ins);
        return ins.getId();
    }

    public Inscriptions identification() {
        HibernateDAO dao = new HibernateDAO();
        Inscriptions ins = null;
        byte[] fpTemplate = new byte[2048];
        int[] sizeFPTemp = new int[1];
        sizeFPTemp[0] = 2048;
        FingerprintSensorEx.ExtractFromImage(mhDB, "fingerprint.bmp", 500, fpTemplate, sizeFPTemp);
        List<Inscriptions> list = dao.findAll(new Inscriptions());
        int max = 0;
        for (Inscriptions inscriptions : list) {
            byte[] fpTemplate1 = inscriptions.getEmpreinte();
            int score = FingerprintSensorEx.DBMatch(mhDB, fpTemplate1, fpTemplate);
            if (max < score) {
                max = score;
                ins = inscriptions;
            }
            System.out.println("score====" + score);
//            System.out.println("id====" + ins.getId());
        }
        return ins;

    }
}
