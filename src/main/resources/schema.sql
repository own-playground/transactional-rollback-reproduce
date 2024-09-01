DROP TABLE IF EXISTS activity CASCADE;
CREATE TABLE activity (
    activity_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '활동 ID',
    user_id BIGINT NOT NULL COMMENT '사용자 ID',
    activity_name VARCHAR(20) NOT NULL COMMENT '활동명',
    PRIMARY KEY (activity_id)
) ENGINE=InnoDB AUTO_INCREMENT=3 CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='유저 활동';
