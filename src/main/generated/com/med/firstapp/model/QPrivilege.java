package com.med.firstapp.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrivilege is a Querydsl query type for Privilege
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrivilege extends EntityPathBase<Privilege> {

    private static final long serialVersionUID = 1651786430L;

    public static final QPrivilege privilege = new QPrivilege("privilege");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public QPrivilege(String variable) {
        super(Privilege.class, forVariable(variable));
    }

    public QPrivilege(Path<? extends Privilege> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrivilege(PathMetadata metadata) {
        super(Privilege.class, metadata);
    }

}

