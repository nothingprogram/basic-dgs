package io.jongyun.learndgs.datsource.problemz.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "problemz")
class Problemz(
    id: UUID,
    var title: String,
    var content: String,
    var tsgs: String,
    @CreationTimestamp
    var createdAt: LocalDateTime,
) {
    @Id
    var id: UUID? = id

    @OneToMany(mappedBy = "problemz", cascade = [CascadeType.REMOVE])
    var solutionzList: MutableList<Solutionz> = mutableListOf()

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    var createdBy: Userz? = null
}