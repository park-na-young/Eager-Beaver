package com.tododev.api.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBanners is a Querydsl query type for UserBanners
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBanners extends EntityPathBase<UserBanners> {

    private static final long serialVersionUID = -852127432L;

    public static final QUserBanners userBanners = new QUserBanners("userBanners");

    public final StringPath ddayContent = createString("ddayContent");

    public final DateTimePath<java.time.LocalDateTime> ddayDate = createDateTime("ddayDate", java.time.LocalDateTime.class);

    public final StringPath fontColor = createString("fontColor");

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath userid = createString("userid");

    public QUserBanners(String variable) {
        super(UserBanners.class, forVariable(variable));
    }

    public QUserBanners(Path<? extends UserBanners> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBanners(PathMetadata metadata) {
        super(UserBanners.class, metadata);
    }

}

