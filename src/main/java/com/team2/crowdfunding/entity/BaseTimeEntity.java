package com.team2.crowdfunding.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 1
@EntityListeners(AuditingEntityListener.class) // 2
public class BaseTimeEntity {

    @CreatedDate// 3
    @Column(name = "localDateTime")
    private LocalDateTime localDateTime;

    @LastModifiedDate // 4
    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;

//1 : 모든 JPA 엔티티들이 BaseTimeEntity를 상속 받을 경우 필드도 컬럼으로 인식하도록 한다.
//
//2 : BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
//
//3 : 엔티티가 생성될 때 생성된 시간이 자동 저장된다.
//
//4 : 조회한 엔티티가 수정될 때 수정된 시간이 자동 저장된다.
}
