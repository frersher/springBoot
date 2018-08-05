package com.shine.mapper;

import com.shine.model.ArticleInfo;
import com.shine.model.ArticleInfoExample;
import com.shine.model.ArticleInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleInfoMapper {
    int countByExample(ArticleInfoExample example);

    int deleteByExample(ArticleInfoExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleInfoWithBLOBs record);

    int insertSelective(ArticleInfoWithBLOBs record);

    List<ArticleInfoWithBLOBs> selectByExampleWithBLOBs(ArticleInfoExample example);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    ArticleInfoWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") ArticleInfoWithBLOBs record, @Param("example") ArticleInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleInfoWithBLOBs record, @Param("example") ArticleInfoExample example);

    int updateByExample(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    int updateByPrimaryKeySelective(ArticleInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleInfoWithBLOBs record);

    int updateByPrimaryKey(ArticleInfo record);
}