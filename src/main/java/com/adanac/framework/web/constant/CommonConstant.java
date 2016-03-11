package com.adanac.framework.web.constant;

/**
 * 公共常量
 * @author adanac
 * @version 1.0
 */
public class CommonConstant {
	/**
	 * 是否对请求的参数进行有安全检查，并进行转码
	 */
	public static final String IS_MSG_SECURITY_CHECK = "_isMsgCheck";

	/**
	 * 用户名session对应的KEY
	 */
	public static final String USER_NAME = "userName";

	/**
	 * 所有菜单信息
	 */
	public static final String ALL_MENU_INFO = "allMenuInfo";

	/**
	 * SESSION一天有效
	 */
	public static final int SESSION_TIME = 24 * 60 * 60;
	/**
	 * 设置链接库存中心最大超时时间
	 */
	public static final int MAX_TIMEOUT_TIME = 60000;
	/**
	 * 商品编码
	 */
	public static final String PRODUCT_CODE = "productCode";

	/**
	 * 商户商品编码
	 */
	public static final String SUPPLIER_CM_CODE = "supplierCmCode";

	/**
	 * 商户编码
	 */
	public static final String SUPPLIER_CODE = "supplierCode";

	/**
	 * 商品型号
	 */
	public static final String CM_MODEL = "cmModel";

	/**
	 * 商品条码
	 */
	public static final String CM_BARCODE = "cmBarcode";

	/**
	 * 商户名称
	 */
	public static final String SUPPLIER_NAME = "supplierName";

	/**
	 * 类目代码
	 */
	public static final String CATEGORY_CODE = "categoryCode";

	/**
	 * 类目名称
	 */
	public static final String CATEGORY_NAME = "categoryName";

	/**
	 * 品牌代码
	 */
	public static final String BRAND_CODE = "brandCode";

	/**
	 * 品牌名称
	 */
	public static final String BRAND_NAME = "brandName";

	/**
	 * 申请编码
	 */
	public static final String APPLY_CODE = "applyCode";

	/**
	 * 商品名称
	 */
	public static final String PRODUCT_NAME = "productName";

	/**
	 * 基本信息编码
	 */
	public static final String BASICINFO_CODE = "basicinfoCode";

	/**
	 * 价格
	 */
	public static final String PRICE = "price";

	/**
	 * 库存数量
	 */
	public static final String QTY = "qty";

	/**
	 * 库存预警值
	 */
	public static final String QTY_WARNING = "qtyWarning";

	/**
	 * 申请状态
	 */
	public static final String APPLY_STATUS = "applyStatus";

	/**
	 * 父申请编码
	 */
	public static final String PARENT_CODE = "parentCode";

	/**
	 * 商品类型
	 */
	public static final String CMMDTY_TYPE = "cmmdtyType";

	/**
	 * 审批意见
	 */
	public static final String APPROVAL = "approval";

	/**
	 * 数据源
	 */
	public static final String DATA_FROM = "dataFrom";

	/**
	 * 动态参数编码
	 */
	public static final String DY_PARAM_CODE = "dyParamCode";

	/**
	 * 动态参数值
	 */
	public static final String DY_PARAM_VALUE = "dyParamValue";

	/**
	 * 动态参数类型
	 */
	public static final String DY_PARAM_TYPE = "dyParamType";

	/**
	 * 动态参数修改标志
	 */
	public static final String DY_MODIFY_FLAG = "dyModifyFlag";

	/**
	 * 基本参数CODE
	 */
	public static final String BASIC_PARAM_CODE = "basicParamCode";

	/**
	 * 基本参数VALUE
	 */
	public static final String BASIC_PARAM_VALUE = "basicParamValue";

	/**
	 * 基本参数修改标志
	 */
	public static final String BASIC_MODIFY_FLAG = "basicModifyFlag";

	/**
	 * 售后
	 */
	public static final String AFTER_SALE = "afterSale";

	/**
	 * 卖点
	 */
	public static final String SELLING_POINTS = "sellingPoints";

	/**
	 * 商品介绍
	 */
	public static final String CM_INTRODUCE = "cmIntroduce";

	/**
	 * 运费模板编码
	 */
	public static final String FREIGHT_CODE = "freightCode";

	/**
	 * 价格记录编号
	 */
	public static final String PRICE_RECORD_CODE = "priceRecordCode";

	/**
	 * 价格类型
	 */
	public static final String PRICE_TYPE = "priceType";

	/**
	 * 销售城市
	 */
	public static final String SALES_CITY = "salesCity";

	/**
	 * 分销渠道
	 */
	public static final String DIST_CHANNEL = "distChannel";

	/**
	 * 库位
	 */
	public static final String PRICE_GROUP = "priceGroup";

	/**
	 * 删除标记
	 */
	public static final String DELETE_FLAG = "deleteFlag";

	/**
	 * 货币码
	 */
	public static final String CURRENCY = "currency";

	/**
	 * 备注
	 */
	public static final String COMMENT = "comment";

	/**
	 * 开始时间
	 */
	public static final String BEGIN_TIME = "beginTime";

	/**
	 * 结束时间
	 */
	public static final String END_TIME = "endTime";

	/**
	 * 创建时间
	 */
	public static final String CREATE_TIME = "createTime";

	/**
	 * 更新时间
	 */
	public static final String UPDATE_TIME = "updateTime";

	/**
	 * 销售状态
	 */
	public static final String SALES_STATUS = "salesStatus";

	/**
	 * 生效日期
	 */
	public static final String EFFECTIVE_DATE = "effectiveDate";

	/**
	 * 销售组织
	 */
	public static final String SALES_ORG = "salesOrg";

	/**
	 * 地点
	 */
	public static final String SITE_CODE = "siteCode";

	/**
	 * 仓库代码
	 */
	public static final String DC_CODE = "dcCode";

	/**
	 * 商品编码前缀（9个0）
	 */
	public static final String PRODUCT_CODE_PREFIX = "000000000";

	/**
	 * 销售城市默认值：000009000000
	 */
	public static final String SALES_CITY_DEFAULT = "000009000000";

	/**
	 * 价格类型-C挂牌价
	 */
	public static final String PRICE_TYPE_C = "40";

	/**
	 * 库位默认值
	 */
	public static final String PRICE_GROUP_DEFAULT = "0001";

	/**
	 * 分销渠道默认值
	 */
	public static final String DIST_CHANNEL_DEFAULT = "50";

	/**
	 * 货币码默认值
	 */
	public static final String CURRENCY_DEFAULT = "CNY";

	/**
	 * 地点默认值
	 */
	public static final String SITE_CODE_DEFAULT = "";

	/**
	 * 空
	 */
	public static final String NULL_VALUE = "";

	/**
	 * 删除标记默认值
	 */
	public static final String DELETE_FLAG_DEFAULT = "";
	/**
	 * 仓库不存在返回值
	 */
	public static final String DC_CODE_NULL = "";
	/**
	 * key
	 */
	public static final String KEY = "key";
	/**
	 * 错误代码
	 */
	public static final String ERROR_CODE = "errorcode";
	/**
	 * 标示位
	 */
	public static final String FLAG = "flag";
	/**
	 * 成功标示
	 */
	public static final String SUCCESSFLAG = "successFlag";
	/**
	 * 字符N，错误
	 */
	public static final String N = "N";
	/**
	 * 字符Y，正确
	 */
	public static final String Y = "Y";
	/**
	 * 0-9999之间
	 */
	public static final String MATCHES_ONE = "[0]|[1-9]\\d{0,4}";
	/**
	 * 初始库存
	 */
	public static final String INVQTYINI = "invQtyIni";
	/**
	 * 目标库存
	 */
	public static final String INVQTYTAR = "invQtyTar";
	/**
	 * 集合
	 */
	public static final String LIST = "list";
	/**
	 * 返回结果
	 */
	public static final String RESULT = "result";

	/**
	 * 分页开始页
	 */
	public static final String PAGE = "page";

	/**
	 * 分页行数参数
	 */
	public static final String PAGE_SIZE = "pageSize";

	/**
	 * 成功Success
	 */
	public static final String SUCCESS = "Success";

	/**
	 * 失败Failure
	 */
	public static final String FAILURE = "Failure";

	/**
	 * 逗号分隔符,
	 */
	public static final String SPLIT_SIGN = ",";

	/**
	 * 信息
	 */
	public static final String MESSAGE = "message";

	/**
	 * 单个引号 '
	 */
	public static final String SING_POINT = "'";

	/**
	 * 单引号逗号 ','
	 */
	public static final String SING_POINT_COMMA = "','";

	/**
	 * 大写 PRODUCTCODE
	 */
	public static final String UPPER_PRODUCT_CODE = "PRODUCTCODE";

	/**
	 * 大写 APPLYCODE
	 */
	public static final String UPPER_APPLY_CODE = "APPLYCODE";

	/**
	 * 大写 CATEGORYCODE
	 */
	public static final String UPPER_CATEGORY_CODE = "CATEGORYCODE";
	/**
	 * b2c返回价格出来信息
	 */
	public static final String PROCESS_STAT = "processStat";

	/**
	 * 分割符|
	 */
	public static final String PARAM_STRING = "\\|";

	/**
	 * 分割符^
	 */
	public static final String PARAM_STRINGF = "\\^";

	/**
	 * 条形码
	 */
	public static final String COMMDTY_ISBN = "commdtyIsbn";

	/**
	 * 商户商品编码
	 */
	public static final String SUPPLIER_CMMDTY_CODE = "supplierCmmdtyCode";

	/**
	 * baseInfoCode
	 */
	public static final String BASIC_INFO_CODE = "basicInfoCode";

	/**
	 * 父申请编码
	 */
	public static final String PARENT_APPLY_CODE = "parentApplyCode";

	/**
	 * 0标识
	 * 
	 */
	public static final String ZERO = "0";

	/**
	 * 销售状态
	 */
	public static final String SAL_STATUS = "salStatus";

	/**
	 * 前台设置销售日期
	 */
	public static final String SET_SELL_DATE = "setSellDate";
	/**
	 * 商家代码前面补齐2个0，凑齐10位
	 */
	public static final String SUPPLIER_CODE_PREFIX = "00";

	/**
	 * 通用商品00
	 */
	public static final String COMMON_STYLE_ZERO = "00";

	/**
	 * 通用商品01
	 */
	public static final String COMMON_STYLE_ONE = "01";

	/**
	 * 接口导出每次默认发送的最大条数
	 */
	public static final int MAX_ROW_DEFAULT = 1000;
	/**
	 * 发送库存中心最大条数
	 */
	public static final int SEND_IMS_ROW = 100;

	/**
	 * 反斜杠 /
	 */
	public static final String SEPARATOR = "/";
	/**
	 * 设置请求返回超时时间
	 */
	public static final int MAX_SOTIMEOUT_TIEM = 10000;

	/**
	 * 规格值
	 */
	public static final String CHARATER_VALUE = "charaterValue";

	/**
	 * 商户状态(店铺屏蔽状态)
	 */
	public static final String SHIELD_FLAG = "shieldFlag";

	/**
	 * 价格下限
	 */
	public static final String MIN_PRICE = "minPrice";

	/**
	 * 价格上限
	 */
	public static final String MAX_PRICE = "maxPrice";

	/**
	 * 页码
	 */
	public static final String PAGE_NO = "pageNo";

	/**
	 * 处理状态
	 */
	public static final String STATUS = "status";
	/**
	 * 分页默认开始
	 */
	public static final int PAGE_START = 1;

	/**
	 * 分页默认行数
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	/**
	 * 该属性主要用于SopMultiParseFile和ParamsUtil类, 用于将文件类型表单参数作为request的属性,属性名即为 autoAttribute
	 */
	public static final String AUTOATTRIBUTE = "autoAttribute";

	public static final String LANGUAGECODE_DEFAULT = "zh";

	public static final String LANGUAGECODE = "languagecode";
	/**
	 * 用户客户端IP
	 */
	public static final String USER_IP = "IP";

	/**
	 * 是否实时
	 */
	public static final String ISREALTIME = "isrealtime";

	/**
	 * 是否实时
	 */
	public static final String DAYOFFSET = "dayOffset";

	private CommonConstant() {
	}
}
