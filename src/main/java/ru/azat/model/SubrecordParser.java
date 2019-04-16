package ru.azat.model;

import io.netty.buffer.ByteBuf;
import ru.azat.model.subrecord.Subrecord;
import ru.azat.model.subrecord.auth.*;
import ru.azat.model.subrecord.commands.SrCommandData;
import ru.azat.model.subrecord.common.SrRecordResponse;
import ru.azat.model.subrecord.teledata.SrAbsAnSensData;
import ru.azat.model.subrecord.teledata.SrAdSensorsData;
import ru.azat.model.subrecord.teledata.SrPosData;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SubrecordParser {

    public static Subrecord parse(ByteBuf parentBuffer, int recordType) {
        short SRT = parentBuffer.readUnsignedByte();
        int SRL = parentBuffer.readUnsignedShortLE();
        int subrecordType = SRT;
        ByteBuf byteBuf = parentBuffer.readBytes(SRL);
        ByteBuf SRD = byteBuf;
        Subrecord result;
        if (recordType == EgtsEnum.SERVICES.EGTS_AUTH_SERVICE.getValue()) {
            if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_RECORD_RESPONSE.getValue()) {
                SrRecordResponse recordResponse = new SrRecordResponse();
                recordResponse.fromByteBuf(byteBuf);
                return recordResponse;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_TERM_IDENTITY.getValue()) {
                SrTermIdentity srTermIdentity = new SrTermIdentity();
                srTermIdentity.fromByteBuf(byteBuf);
                return srTermIdentity;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_MODULE_DATA.getValue()) {
                SrModuleData srModuleData = new SrModuleData();
                srModuleData.fromByteBuf(byteBuf);
                return srModuleData;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_VEHICLE_DATA.getValue()) {
                SrVehicleData srVehicleData = new SrVehicleData();
                srVehicleData.fromByteBuf(byteBuf);
                return srVehicleData;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_AUTH_PARAMS.getValue()) {
                SrAuthParams srAuthParams = new SrAuthParams();
                srAuthParams.fromByteBuf(byteBuf);
                return srAuthParams;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_AUTH_INFO.getValue()) {
                SrAuthInfo srAuthInfo = new SrAuthInfo();
                srAuthInfo.fromByteBuf(byteBuf);
                return srAuthInfo;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_SERVICE_INFO.getValue()) {
                SrServiceInfo srServiceInfo = new SrServiceInfo();
                srServiceInfo.fromByteBuf(byteBuf);
                return srServiceInfo;
            } else if (SRT == EgtsEnum.AUTH_SERVICE_SUBRECORDS.EGTS_SR_RESULT_CODE.getValue()) {
                SrResultCode srResultCode = new SrResultCode();
                srResultCode.fromByteBuf(byteBuf);
                return srResultCode;
            } else {
                throw new NotImplementedException();
            }
        } else if (recordType == EgtsEnum.SERVICES.EGTS_COMMANDS_SERVICE.getValue()) {
            if (SRT == EgtsEnum.COMMANDS_SERVICE_SUBRECORDS.EGTS_SR_RECORD_RESPONSE.getValue()) {
                SrRecordResponse recordResponse = new SrRecordResponse();
                recordResponse.fromByteBuf(byteBuf);
                return recordResponse;
            } else if (SRT == EgtsEnum.COMMANDS_SERVICE_SUBRECORDS.EGTS_SR_COMMAND_DATA.getValue()) {
                SrCommandData srCommandData = new SrCommandData();
                srCommandData.fromByteBuf(byteBuf);
                return srCommandData;
            } else {
                throw new NotImplementedException();
            }
        } else if (recordType == EgtsEnum.SERVICES.EGTS_TELEDATA_SERVICE.getValue()) {
            if (SRT == 0) {
                SrRecordResponse recordResponse = new SrRecordResponse();
                recordResponse.fromByteBuf(byteBuf);
                return recordResponse;
            } else if (SRT == 16) {
                SrPosData srPosData = new SrPosData();
                srPosData.fromByteBuf(byteBuf);
                return srPosData;
            } else if (SRT == 24) {
                SrAbsAnSensData srAbsAnSensData = new SrAbsAnSensData();
                srAbsAnSensData.fromByteBuf(byteBuf);
                return srAbsAnSensData;
            } else if (SRT == 18) {
                SrAdSensorsData srAbsAnSensData = new SrAdSensorsData();
                srAbsAnSensData.fromByteBuf(byteBuf);
                return srAbsAnSensData;
            } else {
                throw new NotImplementedException();
            }
        } else {
            throw new NotImplementedException();
        }
    }

}
