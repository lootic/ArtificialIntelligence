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
		roomxyz1
		roomxyz2
		roomxyz3
		boxxyz1
		itemxyz1
		itemxyz2		
		itemxyz3
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
		(item itemxyz1)
		(item itemxyz2)
		(item itemxyz3)
		(GRIP grip-left)
		(GRIP grip-right)
		(room roomxyz1) 
		(room roomxyz2)		
		(room roomxyz3)
		(DOOR roomxyz1 roomxyz2)
		(DOOR roomxyz2 roomxyz3)		
		(WIDE-DOOR roomxyz1 roomxyz3)
		(box boxxyz1)
		(box-location roomxyz3 boxxyz1)
		(item-location itemxyz1 roomxyz1)		
		(item-location itemxyz2 roomxyz1)		
		(item-location itemxyz3 roomxyz1)
		(in-room room1))

	(:goal 
		(and(item-location item1 room3)		
		(item-location item2 room3)		
		(item-location item3 room2)
		(not(is-lit room1))
		(item-location itemxyz1 roomxyz3)		
		(item-location itemxyz2 roomxyz3)		
		(item-location itemxyz3 roomxyz2)
		(not(is-lit roomxyz1))))
)
