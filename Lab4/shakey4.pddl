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
		roomxyzxyz1
		roomxyzxyz2
		roomxyzxyz3
		boxxyzxyz1
		itemxyzxyz1
		itemxyzxyz2		
		itemxyzxyz3
		roomxyzxyzw1
		roomxyzxyzw2
		roomxyzxyzw3
		boxxyzxyzw1
		itemxyzxyzw1
		itemxyzxyzw2		
		itemxyzxyzw3
		roomxyzxyzwv1
		roomxyzxyzwv2
		roomxyzxyzwv3
		boxxyzxyzwv1
		itemxyzxyzwv1
		itemxyzxyzwv2		
		itemxyzxyzwv3
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
		(in-room room1)
		(item itemxyzxyz1)
		(item itemxyzxyz2)
		(item itemxyzxyz3)
		(GRIP grip-left)
		(GRIP grip-right)
		(room roomxyzxyz1) 
		(room roomxyzxyz2)		
		(room roomxyzxyz3)
		(DOOR roomxyzxyz1 roomxyzxyz2)
		(DOOR roomxyzxyz2 roomxyzxyz3)		
		(WIDE-DOOR roomxyzxyz1 roomxyzxyz3)
		(box boxxyzxyz1)
		(box-location roomxyzxyz3 boxxyzxyz1)
		(item-location itemxyzxyz1 roomxyzxyz1)		
		(item-location itemxyzxyz2 roomxyzxyz1)		
		(item-location itemxyzxyz3 roomxyzxyz1)
		(DOOR roomxyz1 roomxyzxyz1)(item itemxyzxyzw1)
		(item itemxyzxyzw2)
		(item itemxyzxyzw3)
		(GRIP grip-left)
		(GRIP grip-right)
		(room roomxyzxyzw1) 
		(room roomxyzxyzw2)		
		(room roomxyzxyzw3)
		(DOOR roomxyzxyzw1 roomxyzxyzw2)
		(DOOR roomxyzxyzw2 roomxyzxyzw3)		
		(WIDE-DOOR roomxyzxyzw1 roomxyzxyzw3)
		(box boxxyzxyzw1)
		(box-location roomxyzxyzw3 boxxyzxyzw1)
		(item-location itemxyzxyzw1 roomxyzxyzw1)		
		(item-location itemxyzxyzw2 roomxyzxyzw1)		
		(item-location itemxyzxyzw3 roomxyzxyzw1)
		(DOOR roomxyzxyz1 roomxyzxyzw1)
		(item itemxyzxyzwv1)
		(item itemxyzxyzwv2)
		(item itemxyzxyzwv3)
		(GRIP grip-left)
		(GRIP grip-right)
		(room roomxyzxyzwv1) 
		(room roomxyzxyzwv2)		
		(room roomxyzxyzwv3)
		(DOOR roomxyzxyzwv1 roomxyzxyzwv2)
		(DOOR roomxyzxyzwv2 roomxyzxyzwv3)		
		(WIDE-DOOR roomxyzxyzwv1 roomxyzxyzwv3)
		(box boxxyzxyzwv1)
		(box-location roomxyzxyzwv3 boxxyzxyzwv1)
		(item-location itemxyzxyzwv1 roomxyzxyzwv1)		
		(item-location itemxyzxyzwv2 roomxyzxyzwv1)		
		(item-location itemxyzxyzwv3 roomxyzxyzwv1)
		(DOOR roomxyzxyzw1 roomxyzxyzwv1)

)

	(:goal 
		(and(item-location item1 room3)		
		(item-location item2 room3)		
		(item-location item3 room2)
		(not(is-lit room1))
		(item-location itemxyz1 roomxyz3)		
		(item-location itemxyz2 roomxyz3)		
		(item-location itemxyz3 roomxyz2)
		(not(is-lit roomxyz1))
		(item-location itemxyzxyz1 roomxyzxyz3)		
		(item-location itemxyzxyz2 roomxyzxyz3)		
		(item-location itemxyzxyz3 roomxyzxyz2)
		(not(is-lit roomxyzxyz1))
		(item-location itemxyzxyzw1 roomxyzxyzw3)		
		(item-location itemxyzxyzw2 roomxyzxyzw3)		
		(item-location itemxyzxyzw3 roomxyzxyzw2)
		(not(is-lit roomxyzxyzw1))
		(item-location itemxyzxyzwv1 roomxyzxyzwv3)		
		(item-location itemxyzxyzwv2 roomxyzxyzwv3)		
		(item-location itemxyzxyzwv3 roomxyzxyzwv2)
		(not(is-lit roomxyzxyzwv1))
))
)
