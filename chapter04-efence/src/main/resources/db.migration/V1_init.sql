--创建电子地理围栏核心信息表相关
create sequence public.fence_geo_id_seq;
 
create table public.fence_geo_info
(
    id bigint not null default nextval('fence_geo_id_seq'::regclass),
    name character varying(254) collate pg_catalog."default" not null,
    explain character varying(200) collate pg_catalog."default" default ''::character varying,
    city_code character varying(16) collate pg_catalog."default" not null default ''::character varying,
    ad_code character varying(16) collate pg_catalog."default",
    layer_code character varying(16) collate pg_catalog."default" not null,
    region geometry(geometry,4326) not null,
    centre geometry(point,4326),
    area numeric(16,2),
    custom_info jsonb,
    batch_id bigint,
    from_id bigint,
    geo_json text collate pg_catalog."default",
    geo_hash character varying(16)[] collate pg_catalog."default",
    date_range tstzrange,
    time_bucket int4range[],
    state smallint,
    update_time timestamp with time zone,
    create_time timestamp with time zone,
    update_user character varying(32) collate pg_catalog."default" default ''::character varying,
    create_user character varying(32) collate pg_catalog."default" default ''::character varying,
    constraint fence_geo_pkey primary key (id)
)
with (
    oids = false
);
--添加字段备注
comment on table public.fence_geo_info is '电子地理围栏信息表';

comment on column public.fence_geo_info.id is '围栏id';

comment on column public.fence_geo_info.name is '围栏名称';

comment on column public.fence_geo_info.explain is '围栏描述';

comment on column public.fence_geo_info.city_code is '管理归属city_code';

comment on column public.fence_geo_info.ad_code is '管理归属分区编码';

comment on column public.fence_geo_info.layer_code is '地理图层code';

comment on column public.fence_geo_info.region is '围栏描述geometry';

comment on column public.fence_geo_info.centre is '围栏中心点';

comment on column public.fence_geo_info.area is '围栏面积（单位：平方米）';

comment on column public.fence_geo_info.custom_info is '自定义字段数据';

comment on column public.fence_geo_info.batch_id is '批量导入，同一批次标记';

comment on column public.fence_geo_info.from_id is '来源围栏id';

comment on column public.fence_geo_info.geo_json is '冗余围栏的geojson';

comment on column public.fence_geo_info.geo_hash is '围栏覆盖的geohash列表';

comment on column public.fence_geo_info.date_range is '有效期（开始时间、结束时间）';

comment on column public.fence_geo_info.time_bucket is '一天内的有效时间段(分钟表示){[360,480),[600,840]}';

comment on column public.fence_geo_info.state is '0：生效／1：已删除 ／2：失效';

--添加索引
create index idx_fence_geo_centre on public.fence_geo_info using gist(centre);

comment on index public.idx_fence_geo_centre is '地理围栏中心点索引';

create index idx_fence_geo_city_code on public.fence_geo_info using btree(city_code collate pg_catalog."default");

comment on index public.idx_fence_geo_city_code is '地理围栏表citycode索引';

create index idx_fence_geo_region on public.fence_geo_info using gist(region);

comment on index public.idx_fence_geo_region is '地理围栏地理范围索引';