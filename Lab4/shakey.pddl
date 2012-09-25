(define (domain shakey)
	(:requirements :strips) 

	(:predicates	(ROOM ?room)
					(DOOR ?room1 ?room2)
					(WIDE-DOOR ?room1 ?room2)
					(BOX ?box)
					(ITEM ?item)
					(GRIP ?gripper)
					(in-room ?room)
					(box-location ?room ?box)
					(is-lit ?room)
					(left-gripper ?grip)
					(right-gripper ?grip)
					(left-item ?item)
					(right-item ?item)
					(item-location ?item ?room))

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

	(:action grip-left
		:parameters (?item ?room)
		:precondition (and 	(item-location ?item ?room) (is-lit ?room) 
							(in-room ?room)(not (left-gripper ?grip)))
		:effect (and (left-gripper ?item) (not (item-location ?item ?room))))
	
	(:action grip-left
		:parameters (?item ?room)
		:precondition (and 	(left-gripper ?item) (in-room ?room))
		:effect (and (not(left-gripper ?item)) (item-location ?item ?room)))

	(:action grip-right
		:parameters (?item ?room)
		:precondition (and 	(item-location ?item ?room) (is-lit ?room) 
							(in-room ?room)(not (right-gripper ?item)))
		:effect (and (right-gripper ?item) (not (item-location ?item ?room))))
	
	(:action grip-right
		:parameters (?item ?room)
		:precondition (and 	(right-gripper ?item) (in-room ?room))
		:effect (and (not(right-gripper ?item)) (item-location ?item ?room)))
) 
