package ru.egts.core.parser;

import lombok.extern.slf4j.Slf4j;
import ru.egts.core.bean.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Slf4j
public class ResponseParser implements Parser<byte[], Response> {

    private static final byte EGTS_PC_INC_DATAFORM = (byte) 0x84;

    @Override
    public byte[] parse(int start, Response response) {
        byte[] arr = new byte[4];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ByteBuffer.wrap(arr).order(ByteOrder.LITTLE_ENDIAN).putInt(response.getResponsePacketId());
            bos.write(arr, 0, 2);
            bos.write(response.getProcessingResult());

            response.getRecords().forEach(r -> {
                byte[] arrTemp = new byte[4];
                ByteBuffer.wrap(arrTemp).order(ByteOrder.LITTLE_ENDIAN).putInt(r.getConfirmedRecordNumber());
                bos.write(arrTemp, 0, 2);
                bos.write(r.getRecordStatus());
            });

            return bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            //error response
            byte[] responseBytes = new byte[3];
            System.arraycopy(arr, 0, responseBytes, 0, 2);
            responseBytes[2] = EGTS_PC_INC_DATAFORM;
            return responseBytes;
        }
    }
}
