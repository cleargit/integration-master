package com.sham.demo.model;

import com.sham.common.annotation.ExportCsv;
import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "sr_user")
public class SrUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 专长
     */
    private String expertise;

    /**
     * 出生时间
     */
    @Column(name = "birthDate")
    private Integer birthdate;

    /**
     * 范畴
     */
    private String category;

    /**
     * 职级
     */
    private String rank;

    /**
     * 备注
     */
    private String remart;

    /**
     * 权值
     */
    private Integer weight;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    @ExportCsv(title = "名字",order = 2)
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取别名
     *
     * @return alias - 别名
     */
    @ExportCsv(title = "别名",order = 1)
    public String getAlias() {
        return alias;
    }

    /**
     * 设置别名
     *
     * @param alias 别名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    @ExportCsv(title = "性别",order = 3)
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取专长
     *
     * @return expertise - 专长
     */
    public String getExpertise() {
        return expertise;
    }

    /**
     * 设置专长
     *
     * @param expertise 专长
     */
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    /**
     * 获取出生时间
     *
     * @return birthDate - 出生时间
     */
    public Integer getBirthdate() {
        return birthdate;
    }

    /**
     * 设置出生时间
     *
     * @param birthdate 出生时间
     */
    public void setBirthdate(Integer birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 获取范畴
     *
     * @return category - 范畴
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置范畴
     *
     * @param category 范畴
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取职级
     *
     * @return rank - 职级
     */
    public String getRank() {
        return rank;
    }

    /**
     * 设置职级
     *
     * @param rank 职级
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取备注
     *
     * @return remart - 备注
     */
    public String getRemart() {
        return remart;
    }

    /**
     * 设置备注
     *
     * @param remart 备注
     */
    public void setRemart(String remart) {
        this.remart = remart;
    }

    /**
     * 获取权值
     *
     * @return weight - 权值
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置权值
     *
     * @param weight 权值
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}