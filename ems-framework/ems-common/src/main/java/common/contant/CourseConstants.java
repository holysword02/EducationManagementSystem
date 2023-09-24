package common.contant;

public class CourseConstants {

    //课程审核状态
    public final static String AUDIT_DISPAST_STATUS = "202001"; //审核未通过
    public final static String AUDIT_UNPAST_STATUS = "202002"; //未提交
    public final static String AUDIT_COMMIT_STATUS = "202003"; //已提交
    public final static String AUDIT_PASTED_STATUS = "202004"; //审核通过
    public final static String AUDIT_PUBLISHED_STATUS = "202005"; //课程已发布
    public final static String AUDIT_UNLINE_STATUS = "202006"; //课程已下线

    //课程收费状态
    public final static String CHARGE_NO = "201000";//免费
    public final static String CHARGE_YES = "201001";//收费

    //课程等级状态
    public final static String INITIAL_LEVEL = "204001";//初级
    public final static String MIDDLE_LEVEL = "204002";//中级
    public final static String SENIOR_LEVEL = "204003";//高级

    //课程授课模式
    public final static String COURSE_MODE_COMMON_STATUS = "200001";//普通
    public final static String COURSE_MODE_RECORD_STATUS = "200002";//录播
    public final static String COURSE_MODE_LIVE_STATUS = "200003";//直播

    //课程计划发布状态
    public final static String UNPUBLISH_STATUS = "203001";//未发布
    public final static String PUBLISH_STATUS = "203002";//已发布

    //课程计划等级
    public static final Integer FIRST_PARENTID_FLAG = 0;
    public static final Integer FIRST_LEVEL = 1;
    public static final Integer SECEND_LEVEL = 2;
    public static final Integer THIRD_LEVEL = 3;
}
