
GET datatypes
    <sql>SELECT TYPE_ID, TYPE_DESC, RANK FROM DATATYPE_TBL</sql>
POST datatype
    INSERT INTO DATATYPE_TBL (TYPE_DESC, RANK) VALUES (?,?)
        ON CONFLICT (TYPE_DESC)
        DO UPDATE SET RANK = ?
        RETURNING TYPE_ID;
GET Systems
    <sql>SELECT SYSTEM_ID, SOURCE_SYS, SYS_TYPE, LOC_TYPE, THIRD_PARTY_ID, LOCATION FROM SYSTEM_TBL</sql>
POST System
    INSERT INTO SYSTEM_TBL (SOURCE_SYS, SYS_TYPE, LOC_TYPE, THIRD_PARTY_ID, LOCATION) VALUES (?,?,?,?,?)
        ON CONFLICT (SOURCE_SYS,SYS_TYPE)
        DO UPDATE SET LOC_TYPE = ?, THIRD_PARTY_ID = ?, LOCATION = ?
        RETURNING SYSTEM_ID;
GET Locations
    <sql>SELECT RLS_ID, SYSTEM_ID, DATA_TYPE, PATIENT_ID, LAST_UPDATED FROM LOCATOR_TBL WHERE PATIENT_ID=:patientid</sql>
    <sql>SELECT RLS_ID, SYSTEM_ID, DATA_TYPE, PATIENT_ID, LAST_UPDATED FROM LOCATOR_TBL WHERE PATIENT_ID=:patientid AND DATA_TYPE=:datatype</sql>
    <sql>SELECT RLS_ID, SYSTEM_ID, DATA_TYPE, PATIENT_ID, LAST_UPDATED FROM LOCATOR_TBL WHERE (PATIENT_ID=:patientid AND LAST_UPDATED::text &gt; :lastchecked::text)</sql>
    ... suggested by James:
        This is for a consumer wanting to find records for a patient

        Select c.rls_id
        b.type_desc
        c.data_type
        c.last_updated
        from system_tbl a,
        system_type_tbl b,
        locator_tbl c
        where b.type_id = a.sys_type
        and c.system_id = a.system_id
        and c.patient_id = ?????

        if you wan to get the last updated, use the following. I said on the call that you needed the effective date. But I was thinking we needed to return 1 row, but we need to return all rows.

        And c.last_updated >= ???????
POST Location
    INSERT INTO LOCATOR_TBL (SYSTEM_ID, DATA_TYPE, PATIENT_ID) VALUES (?,?,?)
        ON CONFLICT (SYSTEM_ID, DATA_TYPE, PATIENT_ID)
        DO UPDATE SET LAST_UPDATED = CURRENT_TIMESTAMP
        RETURNING RLS_ID;
POST Audit
    INSERT INTO AUDIT_TBL (USER_ID,RLS_ID,PATIENT_ID) VALUES (?,?,?)
        RETURNING AUDIT_ID;
        