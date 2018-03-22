<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="top.zywork.dao.{beanName}DAO">

    <sql id="select_columns">
        {selectColumns}
    </sql>

    <sql id="query_where_clause">
        {queryWhereClause}
    </sql>

    <select id="getById" parameterType="long" resultType="{beanNameLowerCase}DO">
        select
        <include refid="select_columns"/>
        from {tableName} where {primaryTable}.id = #{id} and
        {joinWhereClause}
    </select>

    <select id="listAll" resultType="{beanNameLowerCase}DO">
        select
        <include refid="select_columns"/>
        from {tableName}
        where
        {joinWhereClause}
        order by {primaryTable}.update_time desc, {primaryTable}.create_time desc
    </select>

    <select id="listPage" parameterType="top.zywork.query.PageQuery" resultType="{beanNameLowerCase}DO">
        select
        <include refid="select_columns"/>
        from {tableName}
        where
        {joinWhereClause}
        order by {primaryTable}.update_time desc, {primaryTable}.create_time desc
        limit #{beginIndex}, #{pageSize}
    </select>

    <select id="count" resultType="long">
        select count(*) from {tableName}
        where
        {joinWhereClause}
    </select>

    <select id="listPageByCondition" resultType="{beanNameLowerCase}DO">
        select
        <include refid="select_columns"/>
        from {tableName}
        <where>
            <include refid="query_where_clause"/>
        </where>
        and
        {joinWhereClause}
        order by {primaryTable}.update_time desc, {primaryTable}.create_time desc
        limit #{beginIndex}, #{pageSize}
    </select>

    <select id="countByCondition" parameterType="{beanNameLowerCase}Query" resultType="long">
        select count(*) from {tableName}
        <where>
            <include refid="query_where_clause"/>
        </where>
        and
        {joinWhereClause}
    </select>

</mapper>