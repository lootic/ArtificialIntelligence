(define (domain logistics-strips)
	(:requirements :strips) 

	(:predicates	(ROOM ?room)
					(DOOR ?room1 ?room2)
					(in-room ?room))

	(:action move (?from ?to)
		:precondition (DOOR ?from ?to)
		:effect (and (in-room ?to) (not (in-room ?from))))
)
