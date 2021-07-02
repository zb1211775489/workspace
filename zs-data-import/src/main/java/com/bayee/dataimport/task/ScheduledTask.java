package com.bayee.dataimport.task;


import com.bayee.dataimport.kafka.provincial.ProvincialDataProducer;
import com.bayee.dataimport.kafka.water.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
//    /**
//     * 自然灾害转移人员名单-供地方和厅局调用
//     * ptt_per_disaster_transfer
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePttPerDisasterTransfer() {
//        new ProvincialDataProducer("ptt-per-disaster-transfer","U0098S00287","V1","3a8baae3-72db-4a8f-9e8d-79bc956cae89","U0177Z0001").send();
//    }
//
//    /**
//     * 特种作业人员信息
//     * perm_per_special_work
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePermPerSpecialWork() {
//        new ProvincialDataProducer("perm-per-special-work","U0098S00058","V1","ddd60401-207b-4446-beb8-17bd4ec28f83","U0177Z0001").send();
//    }
//
//    /**
//     * 自然灾害转移责任人信息
//     * gua_inst_disaster_transfer_manager
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeGuaInstDisasterTransferManager() {
//        new ProvincialDataProducer("gua-inst-disaster-transfer-manager","U0098S00232","V1","5b33312f-65bd-41d3-a60f-d728050f49de","U0177Z0001").send();
//    }
//
//    /**
//     * 自然灾害拟转移人员名单-供地方和厅局调用
//     * ptt_per_disaster_to_transfer
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePttPerDisasterToTransfer() {
//        new ProvincialDataProducer("ptt-per-disaster-to-transfer","U0098S00288","V1","47e3c681-adca-4729-adb6-95bad80765f5","U0177Z0001").send();
//    }
//
//    /**
//     * 安全码扫码人员信息表
//     * ptt_per_code_operation_location
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePttPerCodeOperationLocation() {
//        new ProvincialDataProducer("ptt-per-code-operation-location","U0098S00261","V1","cdae78fe-9bfe-4896-aa94-3025686dd8cf","U0177Z0001").send();
//    }
//
//    /**
//     * 地质灾害风险等级预警数据
//     * warn_geo_disaster_risk
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/10 * * * ?")
//    public void executeWarnGeoDisasterRisk() {
//        new ProvincialDataProducer("warn-geo-disaster-risk","U0098S00339","V1","499f7607-1de8-4080-83d4-851851e6e63e","U0177Z0001").send();
//    }
//
//    /**
//     * 地质灾害风险实时预警详情信息
//     * warn_nat_geo_disaster_detail
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnNatGeoDisasterDetail() {
//        new ProvincialDataProducer("warn-nat-geo-disaster-detail","U0098S00260","V1","c5210161-2de2-4bc9-97ed-142f5ed090fe","U0177Z0001").send();
//    }
//
//    /**
//     * 地质灾害风险实时预警信息
//     * warn_nat_geo_disaster_realtime
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnNatGeoDisasterRealtime() {
//        new ProvincialDataProducer("warn-nat-geo-disaster-realtime","U0098S00259","V1","1d135c80-6a3a-4083-8ca0-24f0b98b2327","U0177Z0001").send();
//    }
//
//    /**
//     * 地质灾害风险等级24小时预警
//     * warn_nat_geo_disaster_risk
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnNatGeoDisasterRisk() {
//        new ProvincialDataProducer("warn-nat-geo-disaster-risk","U0098S00258","V1","20d3d689-da2a-4afc-ae05-4ad7b2ecb254","U0177Z0001").send();
//    }
//
//    /**
//     * 地质灾害风险预防区
//     * con_nat_geo_risk_area
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatGeoRiskArea() {
//        new ProvincialDataProducer("con-nat-geo-risk-area","U0098S00188","V1","e8d6206c-7ea8-4337-af0b-3b944372fcf6","U0177Z0001").send();
//    }
//
//    /**
//     * 地质灾害隐患点
//     * con_nat_geo_danger_point
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatGeoDangerPoint() {
//        new ProvincialDataProducer("con-nat-geo-danger-point","U0098S00187","V1","83658199-d3c3-4907-9890-a11a51ccb5d0","U0177Z0001").send();
//    }
//
//    /**
//     * 处罚行为-省应急厅
//     * sup_pun_emer_management
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeSupPunEmerManagement() {
//        new ProvincialDataProducer("sup-pun-emer-management","U0098S00257","V1","cf27b053-3765-4e5c-920e-c8b99b01ba6e","U0177Z0001").send();
//    }
//
//    /**
//     * 检查行为-省应急厅
//     * sup_chk_emer_management
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeSupChkEmerManagement() {
//        new ProvincialDataProducer("sup-chk-emer-management","U0098S00255","V1","d299114b-5a27-45d1-bded-442ad74258d8","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪预警预报信息人工干预记录表
//     * warn_wat_flash_flood_artificial
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnWatFlashFloodArtificial() {
//        new ProvincialDataProducer("warn-wat-flash-flood-artificial","U0098S00253","V1","8116bc21-b4ac-4468-8370-9fc37079055c","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪危险区详情表
//     * con_nat_flash_flood_danger_area
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodDangerArea() {
//        new ProvincialDataProducer("con-nat-flash-flood-danger-area","U0098S00222","V1","5c26df58-ecaa-4d07-93f9-45e0a2cb72f8","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪灾害行政区划名录表
//     * con_nat_flash_flood_district
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodDistrict() {
//        new ProvincialDataProducer("con-nat-flash-flood-district","U0098S00148","V1","91bd8cd8-84b4-4b83-82ba-551bb665b8b4","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪灾害防治对象名录表
//     * con_nat_flash_flood_directory
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodDirectory() {
//        new ProvincialDataProducer("con-nat-flash-flood-directory","U0098S00147","V1","63135188-6a74-478e-8773-8dfba9f5b1ce","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪灾害防治对象信息表
//     * con_nat_flash_flood_object
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodObject() {
//        new ProvincialDataProducer("con-nat-flash-flood-object","U0098S00146","V1","99c56c08-edf3-4d7d-a67d-43081632a855","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪预警预报信息历史表
//     * warn_wat_flash_flood_history
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnWatFlashFloodHistory() {
//        new ProvincialDataProducer("warn-wat-flash-flood-history","U0098S00145","V1","8c86d535-0736-467b-bbee-e03f3ab793bd","U0177Z0001").send();
//    }
//
//    /**
//     * 山洪预警预报信息实时记录表
//     * warn_wat_flash_flood_realtime
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnWatFlashFloodRealtime() {
//        new ProvincialDataProducer("warn-wat-flash-flood-realtime","U0098S00144","V1","8bae1def-3971-41fa-8081-8db7ea81989a","U0177Z0001").send();
//    }
//
    /**
     * 渔船基本信息与实时位置信息
     * rea_nat_fishing_boat
     */
    @Async("MyThreadPool")
    @Scheduled(cron = "0 0/5 * * * ?")
    public void executeReaNatFishingBoat() {
        new ProvincialDataProducer("rea-nat-fishing-boat-test","U0098S00174","V1","acd19ac7-ef30-46a3-bd99-975bfd3b19f9","U0177Z0001").send();
    }
//
//    /**
//     * 安全管理和作业人员证-应急厅
//     * perm_cert_safe_manage_work_emer
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePermCertSafeManageWorkEmer() {
//        new ProvincialDataProducer("perm-cert-safe-manage-work-emer","U0098S00275","V1","fbc3418c-0f81-4a16-b3dc-ab4a31c32f33","U0177Z0001").send();
//    }
//
//    /**
//     * 危险化学品经营带储存企业信息
//     * con_ent_danger_chemical_operate
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntDangerChemicalOperate() {
//        new ProvincialDataProducer("con-ent-danger-chemical-operate","U0098S00041","V1","39d00dd8-4f47-4f56-ab0d-48ab5c85c85c","U0177Z0001").send();
//    }
//
//    /**
//     * 企业基本信息表
//     * leg_enterprise_info
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeLegEnterpriseInfo() {
//        new ProvincialDataProducer("leg-enterprise-info","U0098S00115","V2","e936e587-4114-4aba-a37a-4c0fda8a2ce0","U0177Z0001").send();
//    }
//
//    /**
//     * 危化企业总表
//     * con_ent_danger_chemical_total
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void executeConEntDangerChemicalTotal() {
//        new ProvincialDataProducer("con-ent-danger-chemical-total","U0098S00315","V1","f73b1e14-31cc-4bb0-a550-702c6040f10b","U0177Z0001").send();
//    }
//
//    /**
//     * 重大危险源企业信息表
//     * con_ent_danger_source_ent_info
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntDangerSourceEntInfo() {
//        new ProvincialDataProducer("con-ent-danger-source-ent-info","U0098S00356","V1","06c4ebea-6418-47e9-a668-e4e2f9a764a3","U0177Z0001").send();
//    }
//
//    /**
//     * 值班值守应急事件表
//     * dut_rec_emer_event
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeDutRecEmerEvent() {
//        new ProvincialDataProducer("dut-rec-emer-event","U0098S00197","V1","0cc97438-29ae-47b0-b51d-f4b99db57dac","U0177Z0001").send();
//    }
//
//    /**
//     * 每天企业承诺数据
//     * con_ent_promise_info
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntPromiseInfo() {
//        new ProvincialDataProducer("con-ent-promise-info","U0098S00317","V1","e6b7caab-0268-4ffa-a67a-3c80ac3b0274","U0177Z0001").send();
//    }

//    /**
//     * 自然灾害转移人员名单-供地方和厅局调用
//     * ptt_per_disaster_transfer
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePttPerDisasterTransfer() {
//        new ProvincialDataProducer("ptt-per-disaster-transfer","U0098S00287","V1","3a8baae3-72db-4a8f-9e8d-79bc956cae89","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 特种作业人员信息
//     * perm_per_special_work
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePermPerSpecialWork() {
//        new ProvincialDataProducer("perm-per-special-work","U0098S00058","V1","ddd60401-207b-4446-beb8-17bd4ec28f83","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 自然灾害转移责任人信息
//     * gua_inst_disaster_transfer_manager
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeGuaInstDisasterTransferManager() {
//        new ProvincialDataProducer("gua-inst-disaster-transfer-manager","U0098S00232","V1","5b33312f-65bd-41d3-a60f-d728050f49de","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 自然灾害拟转移人员名单-供地方和厅局调用
//     * ptt_per_disaster_to_transfer
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePttPerDisasterToTransfer() {
//        new ProvincialDataProducer("ptt-per-disaster-to-transfer","U0098S00288","V1","47e3c681-adca-4729-adb6-95bad80765f5","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 安全码扫码人员信息表
//     * ptt_per_code_operation_location
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePttPerCodeOperationLocation() {
//        new ProvincialDataProducer("ptt-per-code-operation-location","U0098S00261","V1","cdae78fe-9bfe-4896-aa94-3025686dd8cf","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 地质灾害风险等级预警数据
//     * warn_geo_disaster_risk
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnGeoDisasterRisk() {
//        new ProvincialDataProducer("warn-geo-disaster-risk","U0098S00339","V1","499f7607-1de8-4080-83d4-851851e6e63e","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 地质灾害风险实时预警详情信息
//     * warn_nat_geo_disaster_detail
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnNatGeoDisasterDetail() {
//        new ProvincialDataProducer("warn-nat-geo-disaster-detail","U0098S00260","V1","c5210161-2de2-4bc9-97ed-142f5ed090fe","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 地质灾害风险实时预警信息
//     * warn_nat_geo_disaster_realtime
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnNatGeoDisasterRealtime() {
//        new ProvincialDataProducer("warn-nat-geo-disaster-realtime","U0098S00259","V1","1d135c80-6a3a-4083-8ca0-24f0b98b2327","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 地质灾害风险等级24小时预警
//     * warn_nat_geo_disaster_risk
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnNatGeoDisasterRisk() {
//        new ProvincialDataProducer("warn-nat-geo-disaster-risk","U0098S00258","V1","20d3d689-da2a-4afc-ae05-4ad7b2ecb254","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 地质灾害风险预防区
//     * con_nat_geo_risk_area
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatGeoRiskArea() {
//        new ProvincialDataProducer("con-nat-geo-risk-area","U0098S00188","V1","e8d6206c-7ea8-4337-af0b-3b944372fcf6","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 地质灾害隐患点
//     * con_nat_geo_danger_point
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatGeoDangerPoint() {
//        new ProvincialDataProducer("con-nat-geo-danger-point","U0098S00187","V1","83658199-d3c3-4907-9890-a11a51ccb5d0","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 处罚行为-省应急厅
//     * sup_pun_emer_management
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeSupPunEmerManagement() {
//        new ProvincialDataProducer("sup-pun-emer-management","U0098S00257","V1","cf27b053-3765-4e5c-920e-c8b99b01ba6e","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 检查行为-省应急厅
//     * sup_chk_emer_management
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeSupChkEmerManagement() {
//        new ProvincialDataProducer("sup-chk-emer-management","U0098S00255","V1","d299114b-5a27-45d1-bded-442ad74258d8","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪预警预报信息人工干预记录表
//     * warn_wat_flash_flood_artificial
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnWatFlashFloodArtificial() {
//        new ProvincialDataProducer("warn-wat-flash-flood-artificial","U0098S00253","V1","8116bc21-b4ac-4468-8370-9fc37079055c","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪危险区详情表
//     * con_nat_flash_flood_danger_area
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodDangerArea() {
//        new ProvincialDataProducer("con-nat-flash-flood-danger-area","U0098S00222","V1","5c26df58-ecaa-4d07-93f9-45e0a2cb72f8","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪灾害行政区划名录表
//     * con_nat_flash_flood_district
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodDistrict() {
//        new ProvincialDataProducer("con-nat-flash-flood-district","U0098S00148","V1","91bd8cd8-84b4-4b83-82ba-551bb665b8b4","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪灾害防治对象名录表
//     * con_nat_flash_flood_directory
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodDirectory() {
//        new ProvincialDataProducer("con-nat-flash-flood-directory","U0098S00147","V1","63135188-6a74-478e-8773-8dfba9f5b1ce","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪灾害防治对象信息表
//     * con_nat_flash_flood_object
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConNatFlashFloodObject() {
//        new ProvincialDataProducer("con-nat-flash-flood-object","U0098S00146","V1","99c56c08-edf3-4d7d-a67d-43081632a855","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪预警预报信息历史表
//     * warn_wat_flash_flood_history
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnWatFlashFloodHistory() {
//        new ProvincialDataProducer("warn-wat-flash-flood-history","U0098S00145","V1","8c86d535-0736-467b-bbee-e03f3ab793bd","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 山洪预警预报信息实时记录表
//     * warn_wat_flash_flood_realtime
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeWarnWatFlashFloodRealtime() {
//        new ProvincialDataProducer("warn-wat-flash-flood-realtime","U0098S00144","V1","8bae1def-3971-41fa-8081-8db7ea81989a","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 渔船基本信息与实时位置信息
//     * rea_nat_fishing_boat
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeReaNatFishingBoat() {
//        new ProvincialDataProducer("rea-nat-fishing-boat","U0098S00174","V1","acd19ac7-ef30-46a3-bd99-975bfd3b19f9","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 安全管理和作业人员证-应急厅
//     * perm_cert_safe_manage_work_emer
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executePermCertSafeManageWorkEmer() {
//        new ProvincialDataProducer("perm-cert-safe-manage-work-emer","U0098S00275","V1","fbc3418c-0f81-4a16-b3dc-ab4a31c32f33","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//
//
//    /**
//     * 危险化学品经营带储存企业信息
//     * con_ent_danger_chemical_operate
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntDangerChemicalOperate() {
//        new ProvincialDataProducer("con-ent-danger-chemical-operate","U0098S00041","V1","39d00dd8-4f47-4f56-ab0d-48ab5c85c85c","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 企业基本信息表
//     * leg_enterprise_info
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeLegEnterpriseInfo() {
//        new ProvincialDataProducer("leg-enterprise-info","U0098S00115","V2","e936e587-4114-4aba-a37a-4c0fda8a2ce0","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 危化企业总表
//     * con_ent_danger_chemical_total
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntDangerChemicalTotal() {
//        new ProvincialDataProducer("con-ent-danger-chemical-total","U0098S00315","V1","f73b1e14-31cc-4bb0-a550-702c6040f10b","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 重大危险源企业信息表
//     * con_ent_danger_source_ent_info
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntDangerSourceEntInfo() {
//        new ProvincialDataProducer("con-ent-danger-source-ent-info","U0098S00356","V1","06c4ebea-6418-47e9-a668-e4e2f9a764a3","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 值班值守应急事件表
//     * dut_rec_emer_event
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeDutRecEmerEvent() {
//        new ProvincialDataProducer("dut-rec-emer-event","U0098S00197","V1","0cc97438-29ae-47b0-b51d-f4b99db57dac","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 每天企业承诺数据
//     * con_ent_promise_info
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeConEntPromiseInfo() {
//        new ProvincialDataProducer("con-ent-promise-info","U0098S00317","V1","e6b7caab-0268-4ffa-a67a-3c80ac3b0274","U0177Z0001",System.currentTimeMillis()).send();
//    }
//
//    /*===================================水利数据==================================*/
//
//    /**
//     * 舟山新城智慧三防
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeAllWaterInfo() {
//        new AllWaterInfoProducers("all-water-info",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 查询测站列表信息
//     */
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeListStations() {
//        new ListStationsProducers("stations-info").send();
//    }
//
//    /**
//     * 查询测站实时雨情信息
//     */
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void executeListStationRain() {
//        new StationRainInfoProducers("station-rain-info",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 查询测站实时水位信息
//     */
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeListStationWater() {
//        new StationWaterInfoProducers("station-water-info",System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 大中型水库基础表
//     * new_att_res_lm_base
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeNewAttResLmBase() {
//        new WaterProducers("new-att-res-lm-base","0a95190e", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 小型水库基础表
//     * new_att_res_s_base
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeNewAttResSBase() {
//        new WaterProducers("new-att-res-s-base","0a952151", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 大中型水库汛期主要特征值运用表
//     * new_fhgc_res_lm_rfkwlv
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeNewFhgcResRfkwlvLm() {
//        new WaterProducers("new-fhgc-res-lm-rfkwlv","0a952618", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 小型水库汛期主要特征值运用表
//     * new_fhgc_res_s_rfkwlv
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeNewFhgcResRfkwlvS() {
//        new WaterProducers("new-fhgc-res-s-rfkwlv","0a95282f", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 大中型水库水位、面积、库容、泄量关系表
//     * new_fhgc_res_lm_rwacdr
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeNewFhgcResLmRwacdr() {
//        new WaterProducers("new-fhgc-res-lm-rwacdr","0a95278f", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 小型水库水位、面积、库容、泄量关系表
//     * new_fhgc_res_s_rwacdr
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeNewFhgcResSRwacdr() {
//        new WaterProducers("new-fhgc-res-s-rwacdr","0a9528d5", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 水库测站表
//     * rel_res_st
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeRelResSt() {
//        new WaterProducers("rel-res-st","0a95384d", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 库(湖)站防洪指标表
//     * st_rsvrfcch_b
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeStRsvrfcchB() {
//        new WaterProducers("st-rsvrfcch-b","215c6f84", System.currentTimeMillis()).send();
//    }
//
//    /**
//     * 河道站防洪指标表
//     * st_rvfcch_b
//     */
//    @Async("MyThreadPool")
//    @Scheduled(cron = "0 0/30 * * * ?")
//    public void executeStRvfcchB() {
//        new WaterProducers("st-rvfcch-b","215c7368", System.currentTimeMillis()).send();
//    }

}
