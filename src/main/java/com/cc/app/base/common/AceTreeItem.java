package com.cc.app.base.common;

/**
 * creater shah on 2018/11/5
 **/
public class AceTreeItem {

    public static final String NODE_FOLDER="folder";

    public static final String NODE_ITEM="item";

    public AceTreeItem(){}
    public AceTreeItem(String id,String pid,String code,String text,String type){
        this.setId(id);
        this.setPid(pid);
        this.setCode(code);
        this.setText(text);
        this.setType(type);

    }

    /**
     * 节点的ID
     */
    private String id;

    /**
     * 父节点的ID
     */
    private String  pid;

    /**
     * 节点的编码（英文）
     */
    private String code ;
    /**
     * 节点的名字
     */
    private String text ;

    /**
     * 节点的类型："item":文件  "folder":目录
     */
    private String type ;

    /**
     * 父节点的ID
     */
    private String groupId;

    /**
     * 子节点的信息
     */
    private AdditionalParameters additionalParameters ;


    public String getType()
    {
        return type ;
    }

    public void setType(String type )
    {
        this .type = type;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public AdditionalParameters getAdditionalParameters()
    {
        return additionalParameters ;
    }

    public void setAdditionalParameters(AdditionalParameters additionalParameters )
    {
        this .additionalParameters = additionalParameters ;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getPid() {
        return pid;
    }


    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }
    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


}
