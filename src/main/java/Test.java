import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public class haha{}

    public static void main(String[] args) {
        Boolean a = null;
        Optional.ofNullable(a).orElse(false);
        if(a){
            System.out.println("a");
        }
    }

    public static Map<Integer,Integer> getEmptyMap(){
        return Collections.emptyMap();
    }
    public final static String str = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"component\": \"order-manage/order-summary\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"订单汇总\"\n" +
            "                    },\n" +
            "                    \"name\": \"订单汇总\",\n" +
            "                    \"path\": \"order-summary\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"order-manage/components/order-detail\",\n" +
            "                    \"hidden\": true,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"订单详情\"\n" +
            "                    },\n" +
            "                    \"name\": \"订单详情\",\n" +
            "                    \"path\": \"detail/:id\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"order-manage/order-dispatch\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"订单调度\"\n" +
            "                    },\n" +
            "                    \"name\": \"订单调度\",\n" +
            "                    \"path\": \"order-dispatch\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"order-manage/load-manage\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"运单管理\"\n" +
            "                    },\n" +
            "                    \"name\": \"运单管理\",\n" +
            "                    \"path\": \"load-manage\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"order-manage/stock-out-manage\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"出库管理\"\n" +
            "                    },\n" +
            "                    \"name\": \"出库管理\",\n" +
            "                    \"path\": \"stock-out-manage\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"order-manage/receipt-manage\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"回单管理\"\n" +
            "                    },\n" +
            "                    \"name\": \"回单管理\",\n" +
            "                    \"path\": \"receipt-manage\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"component\": \"Layout\",\n" +
            "            \"hidden\": false,\n" +
            "            \"meta\": {\n" +
            "                \"icon\": \"\",\n" +
            "                \"title\": \"订单管理\"\n" +
            "            },\n" +
            "            \"name\": \"订单管理\",\n" +
            "            \"path\": \"/order-manage\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"component\": \"capacity-manage/carriers-maching-rules/index\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"承运商匹配规则\"\n" +
            "                    },\n" +
            "                    \"name\": \"承运商匹配规则\",\n" +
            "                    \"path\": \"carriers-maching-rule\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"capacity-manage/maching-rule/index\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"承运类型匹配规则\"\n" +
            "                    },\n" +
            "                    \"name\": \"承运类型匹配规则\",\n" +
            "                    \"path\": \"maching-rule\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"component\": \"Layout\",\n" +
            "            \"hidden\": false,\n" +
            "            \"meta\": {\n" +
            "                \"icon\": \"\",\n" +
            "                \"title\": \"运力统筹\"\n" +
            "            },\n" +
            "            \"name\": \"运力统筹\",\n" +
            "            \"path\": \"/order-manage\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"component\": \"carriers/information/index\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"承运商信息\"\n" +
            "                    },\n" +
            "                    \"name\": \"承运商信息\",\n" +
            "                    \"path\": \"carriers-information\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"carriers/information/components/carriers-added\",\n" +
            "                    \"hidden\": true,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"新增承运商\"\n" +
            "                    },\n" +
            "                    \"name\": \"新增承运商\",\n" +
            "                    \"path\": \"carriers-information/added\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"component\": \"Layout\",\n" +
            "            \"hidden\": false,\n" +
            "            \"meta\": {\n" +
            "                \"icon\": \"\",\n" +
            "                \"title\": \"承运商管理\"\n" +
            "            },\n" +
            "            \"name\": \"承运商管理\",\n" +
            "            \"path\": \"/carriers\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"component\": \"passage/track-inquire/index\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"轨迹查询\"\n" +
            "                    },\n" +
            "                    \"name\": \"轨迹查询\",\n" +
            "                    \"path\": \"track-inquire\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"component\": \"Layout\",\n" +
            "            \"hidden\": false,\n" +
            "            \"meta\": {\n" +
            "                \"icon\": \"\",\n" +
            "                \"title\": \"在途管理\"\n" +
            "            },\n" +
            "            \"name\": \"在途管理\",\n" +
            "            \"path\": \"/passage\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"component\": \"system/org-structure-manage\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"组织结构管理\"\n" +
            "                    },\n" +
            "                    \"name\": \"组织结构管理\",\n" +
            "                    \"path\": \"orgStructureManage\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"system/role-manage\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"角色管理\"\n" +
            "                    },\n" +
            "                    \"name\": \"角色管理\",\n" +
            "                    \"path\": \"roleManage\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"system/user-list\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"用户列表\"\n" +
            "                    },\n" +
            "                    \"name\": \"用户列表\",\n" +
            "                    \"path\": \"userList\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"system/sys-configure\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"数据字典\"\n" +
            "                    },\n" +
            "                    \"name\": \"数据字典\",\n" +
            "                    \"path\": \"configure\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"system/sys-journal\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"系统日志\"\n" +
            "                    },\n" +
            "                    \"name\": \"系统日志\",\n" +
            "                    \"path\": \"journal\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"component\": \"system/ware-house\",\n" +
            "                    \"hidden\": false,\n" +
            "                    \"meta\": {\n" +
            "                        \"icon\": \"\",\n" +
            "                        \"title\": \"仓库管理\"\n" +
            "                    },\n" +
            "                    \"name\": \"仓库管理\",\n" +
            "                    \"path\": \"wareHouse\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"component\": \"Layout\",\n" +
            "            \"hidden\": false,\n" +
            "            \"meta\": {\n" +
            "                \"icon\": \"\",\n" +
            "                \"title\": \"系统管理\"\n" +
            "            },\n" +
            "            \"name\": \"系统管理\",\n" +
            "            \"path\": \"/system\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

}
