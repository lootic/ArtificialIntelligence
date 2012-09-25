(define (domain shakey)
	(:requirements :strips) 

	(:predicates	(ROOM ?room)
					(DOOR ?room1 ?room2)
					(WIDE-DOOR ?room1 ?room2)
					(GRIP ?grip)
					(BOX ?box)
					(ITEM ?item)
					(in-room ?room)
					(box-location ?room ?box)
					(is-lit ?room)
					(gripped-item ?item ?grip)
					(item-location ?item ?room)
					(gripped ?grip))

	(:action move 
		:parameters (?from ?to)
		:precondition (and (in-room ?from) (or	(DOOR ?from ?to)(DOOR ?to ?from)
												(WIDE-DOOR ?from ?to)
												(WIDE-DOOR ?to ?from)))
		:effect (and (in-room ?to) (not (in-room ?from))))

	(:action move_box 
		:parameters (?box ?from ?to)
		:precondition (and 	(in-room ?from) (box-location ?from ?box) 
							(or (WIDE-DOOR ?from ?to)(WIDE-DOOR ?to ?from)))
		:effect (and (box-location ?to ?box) (not (box-location ?from ?box))))

	(:action turn_light_on
		:parameters (?room ?box)
		:precondition (and (in-room ?room)(box-location ?room ?box))
		:effect (is-lit ?room))

	(:action turn_light_off
		:parameters (?room ?box)
		:precondition (and (in-room ?room)(box-location ?room ?box))
		:effect (not (is-lit ?room)))

	(:action grip
		:parameters (?room ?item ?grip)
		:precondition 	(and (not (used ?grip)) (in-room ?room) (is-lit ?room) 
						(item-location ?item ?room))
		:effect (and (used ?grip) (gripped-item ?item ?grip) 
				(not (item-location ?item ?room))))
	
	(:action drop
		:parameters (?room ?item ?grip)
		:precondition (and (in-room ?room) (gripped-item ?grip ?item))
		:effect (and (not (used ?grip)) (not(gripped-item ?grip ?item)) 
				(item-location ?item ?room)))
) 
