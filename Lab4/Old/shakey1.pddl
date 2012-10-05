"Debugging problem, testing that everything works as it should"

(define (problem shakey1)
    (:domain shakey)
	(:objects
		room1
		room2
		room3
		box1
		item1
		item2
		item3
		grip-left
		grip-right
	)

	(:init
		(ITEM item1)
		(ITEM item2)
		(ITEM item3)
		(GRIP grip-left)
		(GRIP grip-right)
		(ROOM room1) 
		(ROOM room2)		
		(ROOM room3)
		(DOOR room1 room2)
		(DOOR room2 room3)
		(DOOR room1 roomxyz1)	
		(WIDE-DOOR room1 room3)
		(BOX box1)
		(box-location room3 box1)
		(item-location item1 room1)		
		(item-location item2 room1)		
		(item-location item3 room1)
		(in-room room1))

	(:goal 
		(and(item-location item1 room3)		
		(item-location item2 room3)		
		(item-location item3 room2)
		(not(is-lit room1))))
)
