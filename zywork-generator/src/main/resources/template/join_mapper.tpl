<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="top.zywork.dao.{beanName}DAO">

    <insert id="save" parameterType="{beanNameLowerCase}DO">
        insert into {primaryTable}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            {insertColumns}
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            {insertValues}
        </trim>
    </insert>

    <delete id="remove" parameterType="{beanNameLowerCase}DO">
        delete from {primaryTable} where id = #{{primaryId}}
    </delete>

    <delete id="removeById" parameterType="long">
        delete from {primaryTable} where id = #{id}
    </delete>

    <delete id="removeByIds">
        delete from {primaryTable} where id in
        <foreach item="id" collection="array" separator="," open="(" close=")">
            #{id}
        </foreach>
    </delete>

    <update id="update" parameterType="{beanNameLowerCase}DO">
        update {primaryTable}
        <set>
            {setClause}
        </set>
        where id = #{{primaryId}}
    </update>

    <update id="updateActiveStatus" parameterType="top.zywork.query.StatusQuery">
        update {primaryTable} set is_active = #{status} where id = #{id}
    </update>

    <sql id="select_columns">
        {selectColumns}
    </sql>

    <sql id="query_where_clause">
        {queryWhereClause}
        and
        {joinWhereClause}
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
        order by
        <if test="sort != null and sort != ''">
            ${sort} ${order}
        </if>
        <if test="sort == null or sort == ''">
            {primaryTable}.update_time desc, {primaryTable}.create_time desc
        </if>
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
        order by
        <if test="pager.sort != null and pager.sort != ''">
            ${pager.sort} ${pager.order}
        </if>
        <if test="pager.sort == null or pager.sort == ''">
            {primaryTable}.update_time desc, {primaryTable}.create_time desc
        </if>
        limit #{pager.beginIndex}, #{pager.pageSize}
    </select>

    <select id="countByCondition" resultType="long">
        select count(*) from {tableName}
        <where>
            <include refid="query_where_clause"/>
        </where>
    </select>

</mapper>