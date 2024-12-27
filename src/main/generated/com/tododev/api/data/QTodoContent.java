package com.tododev.api.data;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodoContent is a Querydsl query type for TodoContent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodoContent extends EntityPathBase<TodoContent> {

    private static final long serialVersionUID = -401608177L;

    public static final QTodoContent todoContent1 = new QTodoContent("todoContent1");

    public final StringPath todoContent = createString("todoContent");

    public final StringPath userid = createString("userid");

    public QTodoContent(String variable) {
        super(TodoContent.class, forVariable(variable));
    }

    public QTodoContent(Path<? extends TodoContent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodoContent(PathMetadata metadata) {
        super(TodoContent.class, metadata);
    }

}

