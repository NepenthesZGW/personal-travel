package com.zgw.personaltravel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgw.personaltravel.entity.ResultBody;
import com.zgw.personaltravel.entity.Travellog;
import com.zgw.personaltravel.entity.TravellogExample;
import com.zgw.personaltravel.mapper.TravellogDAO;
import com.zgw.personaltravel.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TravelServiceImpl  implements TravelService {

    @Autowired
    TravellogDAO travellogDAO;

    @Override
    public ResultBody<Travellog> createLog(Travellog travellog) {

        TravellogExample example=new TravellogExample();
         example.createCriteria().andTitleEqualTo(travellog.getTitle());
        int insert=-1;
        if ( travellogDAO.selectByExample(example).isEmpty()) {
             insert = travellogDAO.insert(travellog);
        }
        List<Travellog> travellogs=null;
        if (insert==1){
            travellogs = travellogDAO.selectByExample(example);
        }

        return travellogs!=null&&travellogs.size()==1?new ResultBody<Travellog>().setMsg("成功").setCode(200).setBody(travellogs.get(0))
                :new ResultBody<>().setMsg("您已创建该日志").setCode(500);
    }

    @Override
    public PageInfo<Travellog> findByPage(Integer pageNum, Integer pageSize ,String keyWord) {

        System.out.println("keyword:"+keyWord);
        PageHelper.startPage(pageNum, pageSize);
        if (keyWord==null||keyWord.trim().equals("")) {
            System.out.println("正常查询");
            TravellogExample example = new TravellogExample();
            example.createCriteria().andOvertEqualTo("y");
            return new PageInfo<Travellog>(travellogDAO.selectByExample(example), 5);

        }else{
            System.out.println("模糊查询");
            return new PageInfo<Travellog>(travellogDAO.findByKeyWord(keyWord,"y"), 5);
        }
    }

    @Override
    public ResultBody<Travellog> change(Travellog log) {

        int i = travellogDAO.updateByPrimaryKeySelective(log);

        return i==1?new ResultBody().setCode(200).setMsg("成功"):new ResultBody().setCode(500).setMsg("失败");
    }

    @Override
    public PageInfo<Travellog> findByPageAndPersonId(Integer pageNum, Integer pageSize,Integer personId) {

        PageHelper.startPage(pageNum, pageSize);

        TravellogExample example=new TravellogExample();
        example.createCriteria().andCreateidEqualTo(personId);
        return new PageInfo<Travellog>(travellogDAO.selectByExample(example),5);
    }

    @Override
    public ResultBody deleteTravel(int tarvelId) {

        int i = travellogDAO.deleteByPrimaryKey(tarvelId);

        return i==1?ResultBody.success:ResultBody.error;
    }
}
