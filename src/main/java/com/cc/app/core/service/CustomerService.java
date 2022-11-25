package com.cc.app.core.service;

import com.cc.app.base.common.PageModel;
import com.cc.app.base.common.RtnData;
import com.cc.app.base.service.SeqService;
import com.cc.app.base.utils.UUIDGenUtil;
import com.cc.app.core.dao.CustomerMapper;
import com.cc.app.core.model.Customer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 公司管理
 */
@Service
public class CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SeqService seqService;


    public PageModel queryPageList(Map<String, Object> params, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        Page page = customerMapper.queryPageList(params);//Page本身是一个ArrayList对象，转换为json时不会保留分页信息
        PageInfo pageInfo = page.toPageInfo();//将page转换成pageInfo会保存分页信息返回
        return new PageModel(pageInfo);
    }

    private void insertDriverRecord(Customer customer) {
        customerMapper.insert(customer);
    }

    public Customer getInfo(String id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加司机信息
     * @param params
     * @throws Exception
     */
    public RtnData insert(Map<String,Object> params) throws Exception{
        String corpNo = (String) params.get("corpNo");
        String fax = (String) params.get("fax");
        String customerName = (String) params.get("customerName");
        String customerIndex= (String) params.get("customerIndex");
        String note= (String) params.get("note");
        String tel= (String) params.get("tel");
        String idSeq = seqService.getSeqNo("seq_cec_customerbl");
        String id = corpNo = idSeq;
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCorpno(corpNo);
        customer.setCustomerid(idSeq);
        customer.setCustomerindex(customerIndex);
        customer.setCustomername(customerName);
        customer.setFax(fax);
        customer.setNote(note);
        customer.setTel(tel);
        customerMapper.insertSelective(customer);
        return RtnData.ok();
    }

    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData update(Map<String,Object> params) throws Exception{
        String id=(String) params.get("id");
//        String driverId= (String) params.get("driverId");
        String driverName = (String) params.get("driverName");
        String tel = (String) params.get("tel");
        String note = (String) params.get("note");
        String flg= (String) params.get("flg");
        String fax = (String) params.get("fax");
        String customerName = (String) params.get("customerName");
        String customerIndex= (String) params.get("customerIndex");
        String corpNo = (String) params.get("corpNo");
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCorpno(corpNo);
//        customer.setCustomerid(id); 不修改id
        customer.setCustomerindex(customerIndex);
        customer.setCustomername(customerName);
        customer.setFax(fax);
        customer.setNote(note);
        customer.setTel(tel);
        customerMapper.updateByPrimaryKeySelective(customer);
        return RtnData.ok();
    }
    /**
     * 更新司机信息
     * @param params
     * @throws Exception
     */
    public RtnData delete(Map<String,Object> params) throws Exception{
        String id = (String) params.get("id");
        customerMapper.deleteByPrimaryKey(id);
        return RtnData.ok();
    }

    public Object comboBox(String unitNo) {
        return customerMapper.comboBox(unitNo);
    }

    public Object comboBox2(String unitNo) {
        return customerMapper.comboBox2(unitNo);
    }
}
