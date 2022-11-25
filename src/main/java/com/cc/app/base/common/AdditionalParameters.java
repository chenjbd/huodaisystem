package com.cc.app.base.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * creater shah on 2018/11/5
 **/
public class AdditionalParameters {

    public AdditionalParameters(){}
    public AdditionalParameters(Map<String, AceTreeItem> maps){
        this.setChildren(maps);
    }
    public AdditionalParameters(boolean itemSelected){
        this.setItemSelected(itemSelected);
    }
    /**
     * 子节点列表
     */
    private Map<String, AceTreeItem> children = new HashMap<String, AceTreeItem>();

    /**
     * 是否有选中属性
     */
    @JsonProperty( "item-selected" )
    private boolean itemSelected ;

    public boolean isItemSelected()
    {
        return itemSelected ;
    }

    public void setItemSelected( boolean itemSelected )
    {
        this.itemSelected = itemSelected;
    }

    public Map<String, AceTreeItem> getChildren()
    {
        return children ;
    }

    public void setChildren(Map<String, AceTreeItem> children )
    {
        this .children = children;
    }

}
