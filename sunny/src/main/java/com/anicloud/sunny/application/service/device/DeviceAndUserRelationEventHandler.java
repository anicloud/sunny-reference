package com.anicloud.sunny.application.service.device;

import com.anicloud.sunny.application.assemble.DeviceAndUserRelationDtoAssembler;
import com.anicloud.sunny.application.dto.device.DeviceAndUserRelationDto;
import com.anicloud.sunny.application.dto.user.UserDto;
import com.anicloud.sunny.domain.model.device.DeviceAndUserRelation;
import com.anicloud.sunny.infrastructure.persistence.domain.device.DeviceAndUserRelationDao;
import com.anicloud.sunny.infrastructure.persistence.service.DeviceAndUserRelationPersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wyf on 16-10-13.
 */
@Service(value = "deviceAndUserRelationServcie")
@Transactional
public class DeviceAndUserRelationEventHandler implements DeviceAndUserRelationServcie{

    @Resource
    private DeviceAndUserRelationPersistenceService deviceAndUserRelationPersistenceService;

    @Override
    public DeviceAndUserRelationDto saveRelation(DeviceAndUserRelationDto relationDto) {
        DeviceAndUserRelation relation = DeviceAndUserRelationDtoAssembler.toRelation(relationDto);
        relation = DeviceAndUserRelation.save(deviceAndUserRelationPersistenceService, relation);
        return DeviceAndUserRelationDtoAssembler.toDto(relation);
    }

    @Override
    public void batchSave(List<DeviceAndUserRelationDto> relationDtoList) {
        for (DeviceAndUserRelationDto relationDto : relationDtoList) {
            saveRelation(relationDto);
        }
    }

    @Override
    public DeviceAndUserRelationDto modifyRelation(DeviceAndUserRelationDto relationDto) {
        DeviceAndUserRelation relation = DeviceAndUserRelationDtoAssembler.toRelation(relationDto);
        relation = DeviceAndUserRelation.modify(deviceAndUserRelationPersistenceService, relation);
        return DeviceAndUserRelationDtoAssembler.toDto(relation);
    }

    @Override
    public void batchModify(List<DeviceAndUserRelationDto> relationDtoList) {
        for (DeviceAndUserRelationDto relationDto : relationDtoList) {
            modifyRelation(relationDto);
        }
    }

    @Override
    public void removeRelation(DeviceAndUserRelationDto relationDto) {
        DeviceAndUserRelation.remove(deviceAndUserRelationPersistenceService, DeviceAndUserRelationDtoAssembler.toRelation(relationDto));
    }

    @Override
    public void removeRelationsWithDeviceId(String identificationCode) {
        deviceAndUserRelationPersistenceService.removeRelationsWithDeviceId(identificationCode);
    }

    @Override
    public void batchRemove(List<DeviceAndUserRelationDto> relations) {
        for (DeviceAndUserRelationDto relationDto : relations){
            removeRelation(relationDto);
        }
    }

    @Override
    public List<DeviceAndUserRelationDto> getRelationsByUser(UserDto userDto) {
        List<DeviceAndUserRelation> relations = DeviceAndUserRelation.getRelationsByHashUserId(deviceAndUserRelationPersistenceService,userDto.hashUserId);
        return DeviceAndUserRelationDtoAssembler.toDtoList(relations);
    }

    @Override
    public DeviceAndUserRelationDto getDeviceAndUserRelation(String identificationCode, Long hashUserId) {
        DeviceAndUserRelation relation = DeviceAndUserRelation.getRelaionByDeviceIdAndHashUserId(deviceAndUserRelationPersistenceService,identificationCode,hashUserId);
        return DeviceAndUserRelationDtoAssembler.toDto(relation);
    }

    @Override
    public List<Long> findUserIdByDeviceId(String deviceId) {
        return deviceAndUserRelationPersistenceService.getHashUserIdByDeviceId(deviceId);
    }

    @Override
    public List<DeviceAndUserRelationDto> getRelationsByDeviceId(String identificationCode) {
        List<DeviceAndUserRelationDao> relationDaos = deviceAndUserRelationPersistenceService.getRelationsByDeviceId(identificationCode);
        return DeviceAndUserRelationDtoAssembler.toDtoList(DeviceAndUserRelation.toRelationList(relationDaos));
    }
}
