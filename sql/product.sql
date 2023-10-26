--���̺� ����
drop table product;

--������ ����
drop sequence p_p_ID_seq;

--ȸ��

create table PRODUCT(
    PID         number(8) ,
    PNAME       VARCHAR2(40),
    QUANTITY    NUMBER(10) ,
    PRICE       NUMBER(10) 
);
--�⺻Ű ����
alter table product add constraint PRODUCT_PRODUCT_ID_pk primary key(PID);

--��������
alter table product modify pname constraint  product_pid_nn not null;
alter table product modify QUANTITY constraint  product_QUANTITY_nn not null;
alter table product modify price constraint  product_price_nn not null;

create sequence p_p_ID_seq;

insert into product (pid,pname,quantity,price)
    values(p_p_ID_seq.nextval, 'Ŀ��', '10', '1004');
    insert into product (pid,pname,quantity,price)
    values(p_p_ID_seq.nextval, 'Ŀ��', '10', '10040');

select * from product;
commit;