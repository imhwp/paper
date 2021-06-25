package us.zoom.checkin.entity;

import com.google.common.base.Strings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "user")
@ApiModel("用户实体")
public class User implements Serializable {

    @ApiModelProperty("用户uuid")
    @Id
    @Column(name = "id")
    private String id;

    @ApiModelProperty("用户邮箱")
    @Column(name = "username")
    private String userEmail;

    @ApiModelProperty("用户名")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("联系方式")
    @Column(name = "telno")
    private String telno;

    @ApiModelProperty("角色id")
    @Column(name = "role_id")
    private Integer roleId;

    @ApiModelProperty("用户密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty("更新时间")
    @Column(name = "update_time")
    private Date updateTime;

    @ApiModelProperty("楼层")
    @Column(name = "floor")
    private String floor;

    @ApiModelProperty("部门")
    @Column(name = "department")
    private String department;

    @ApiModelProperty("删除标志")
    @Column(name = "deleted")
    private int deleted;

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        if(Strings.isNullOrEmpty(user.getDepartment()) || Strings.isNullOrEmpty(user.getFloor()) ||
                Strings.isNullOrEmpty(user.getUserEmail()) || Strings.isNullOrEmpty(user.getName())){
            return false;
        }
        return user.getDepartment().equals(department) &&
                user.getFloor().equals(floor) &&
                user.getUserEmail().equals(userEmail) &&
                user.getName().equals(name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", telno='" + telno + '\'' +
                ", roleId=" + roleId +
                ", password='" + password + '\'' +
                ", updateTime=" + updateTime +
                ", floor='" + floor + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
