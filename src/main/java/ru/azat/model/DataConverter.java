/*
 * Copyright 2018 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.azat.model;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public final class DataConverter {

    private DataConverter() {
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static byte[] parseHex(String string) {
        try {
            return Hex.decodeHex(string.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String printHex(byte[] data) {
        return Hex.encodeHexString(data);
    }

    public static byte[] parseBase64(String string) {
        return Base64.decodeBase64(string);
    }

    public static String printBase64(byte[] data) {
        return Base64.encodeBase64String(data);
    }

}
