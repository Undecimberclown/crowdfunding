package com.team2.crowdfunding.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 1
@EntityListeners(AuditingEntityListener.class) // 2
public class BaseTimeEntity {

    @CreatedDate // 3
    private LocalDateTime start_date;//LocalDateTime localDateTime;

    @LastModifiedDate // 4
    private LocalDateTime end_date;//LocalDateTime modifiedDate;

//1 : 모든 JPA 엔티티들이 BaseTimeEntity를 상속 받을 경우 필드도 컬럼으로 인식하도록 한다.
//
//2 : BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
//
//3 : 엔티티가 생성될 때 생성된 시간이 자동 저장된다.
//
//4 : 조회한 엔티티가 수정될 때 수정된 시간이 자동 저장된다.
// 엔티티-생성-시간수정-시간-자동화
// https://velog.io/@nyong_i/%EC%97%94%ED%8B%B0%ED%8B%B0-%EC%83%9D%EC%84%B1-%EC%8B%9C%EA%B0%84%EC%88%98%EC%A0%95-%EC%8B%9C%EA%B0%84-%EC%9E%90%EB%8F%99%ED%99%94-JPA-Auditing
}