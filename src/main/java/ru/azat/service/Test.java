package ru.azat.service;

import io.netty.buffer.ByteBuf;
import ru.azat.model.DataConverter;
import ru.azat.model.Package;
import ru.azat.utils.EgtsDecoderUtils;
import ru.egts.core.bean.GlonassData;
import ru.egts.core.parser.GlonassDataParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) {

        String[] egtsString =
                {"0100010b002200ecf101ca1700edf101ac71d60c0101011400ac71d60c02383636313932303339373638343932710d0100010b001301eef1018a0801eff101ac71d60c0202101800bf904a112e680d98dccf297c914402442ec80c8110000000120300000000101800c4904a11eb940d98953a2a7c913a024a2fc80c8110000000120300000000101800c6904a1140a70d98ac7b2a7c9144025030c80c8110000000120300000000101800ce904a111bc00d98400f2b7c9108025631c80c8110000000120300000000101800d0904a117cbf0d981b382b7c9108025d31c80c8110000000120300000000101800d1904a11ffbc0d98fd4b2b7c9112026231c80c8110000000120300000000101800d2904a1107b80d9807602b7c9112026731c80c8110000000120300000000101800d4904a112ea80d98f4872b7c911c026d32c80c8110000000120300000000ab2c",
                        "0100010b00220026f2011e170027f201b085110e0101011400b085110e02323336303239333630000000000000b1ac0100010b006e0028f20109630029f201b085110e0202101800c2904a116d1f9d995e67357b8100000000000000106c0100120300000000101800d2904a116d1f9d995e67357b8100000028000000106c0100120300000000101800e0904a116d1f9d995e67357b8100000000000000106c0100120300000000ceaa",
                        "0100010b00690034f2018d5e0035f201b5b0abef0202101800df904a11a1c9a1998d873f7b91fe01f800000000106601001804000100000018040002000000120300000000101800e5904a11d2a8a1992d1c3f7b91d6810300000000106901001804000100000018040002000000120300000000dd3c",
                };
        printAllBytes(Arrays.asList(egtsString));
    }

    public static void printAllBytes(List<String> data) {
        data.forEach(e -> {
            Package aPackage = new Package(e);
            aPackage.decode();
            try {
                ByteBuf byteBuf = aPackage.encode();
                String result = EgtsDecoderUtils.toString(byteBuf);
                System.out.println(result);
                System.out.println(e);
            }catch (NotImplementedException ex){
                System.out.println("error");
            }
//            aPackage.getFrame().getRecordList().forEach(r -> {
//                System.out.println("Add record. Length: " + r.getRL() + ", subrecords count: " + r.getSubrecordList().size());
//            });
        });
//        GlonassDataParser parser = new GlonassDataParser();
//        data.forEach(hex -> {
//            GlonassData glonassData = parser.parse(0, DataConverter.hexStringToByteArray(hex));
//            System.out.println(glonassData.getRecords().toString());
//        });
    }

}
