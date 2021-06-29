//package us.zoom.checkin.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.google.common.base.Strings;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.*;
//
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Data
//@ApiModel("用户实体")
//@TableName("user")
//@Builder
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
//public class User implements Serializable {
//
//    @ApiModelProperty("用户uuid")
//    @TableId(value = "Id",type = IdType.AUTO)
//    private String id;
//
//    @ApiModelProperty("用户邮箱")
//    private String userEmail;
//
//    @ApiModelProperty("用户名")
//    private String name;
//
//    @ApiModelProperty("联系方式")
//    private String telno;
//
//    @ApiModelProperty("角色id")
//    private Integer roleId;
//
//    @ApiModelProperty("用户密码")
//    private String password;
//
//    @ApiModelProperty("更新时间")
//    private Date updateTime;
//
//    @ApiModelProperty("楼层")
//    private String floor;
//
//    @ApiModelProperty("部门")
//    private String department;
//
//    @ApiModelProperty("删除标志")
//    private int deleted;
//
//    @Override
//    public boolean equals(Object obj) {
//        User user = (User) obj;
//        if(Strings.isNullOrEmpty(user.getDepartment()) || Strings.isNullOrEmpty(user.getFloor()) ||
//                Strings.isNullOrEmpty(user.getUserEmail()) || Strings.isNullOrEmpty(user.getName())){
//            return false;
//        }
//        return user.getDepartment().equals(department) &&
//                user.getFloor().equals(floor) &&
//                user.getUserEmail().equals(userEmail) &&
//                user.getName().equals(name);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id='" + id + '\'' +
//                ", userEmail='" + userEmail + '\'' +
//                ", name='" + name + '\'' +
//                ", telno='" + telno + '\'' +
//                ", roleId=" + roleId +
//                ", password='" + password + '\'' +
//                ", updateTime=" + updateTime +
//                ", floor='" + floor + '\'' +
//                ", department='" + department + '\'' +
//                '}';
//    }
//}
