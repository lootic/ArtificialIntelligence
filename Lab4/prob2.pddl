(define (problem shakey1)	
    (:domain shakey)
	(:objects
		xxD21room1
		xxD21room2
		xxD21room3
		xxD21box1
		xxD21item1
		xxD21item2		
		xxD21item3
		xxD1room1
		xxD1room2
		xxD1room3
		xxD1box1
		xxD1item1
		xxD1item2		
		xxD1item3

		grip-left
		grip-right
	)

	(:init
		(ITEM xxD21item1)
		(ITEM xxD21item2)
		(ITEM xxD21item3)
		(ROOM xxD21room1) 
		(ROOM xxD21room2)		
		(ROOM xxD21room3)
		(DOOR xxD21room1 xxD21room2)
		(DOOR xxD21room2 xxD21room3)
		(WIDE-DOOR xxD21room1 xxD21room3)
		(BOX xxD21box1)
		(box-location xxD21room3 xxD21box1)
		(item-location xxD21item1 xxD21room1)		
		(item-location xxD21item2 xxD21room1)		
		(item-location xxD21item3 xxD21room1)
		(ITEM xxD1item1)
		(ITEM xxD1item2)
		(ITEM xxD1item3)
		(ROOM xxD1room1) 
		(ROOM xxD1room2)		
		(ROOM xxD1room3)
		(DOOR xxD1room1 xxD1room2)
		(DOOR xxD1room2 xxD1room3)
		(WIDE-DOOR xxD1room1 xxD1room3)
		(BOX xxD1box1)
		(box-location xxD1room3 xxD1box1)
		(item-location xxD1item1 xxD1room1)		
		(item-location xxD1item2 xxD1room1)		
		(item-location xxD1item3 xxD1room1)

		(DOOR xxD1room1 xxD21room1)

		(GRIP grip-left)
		(GRIP grip-right)
		(in-room xxD21room1))
	(:goal 
		(and(item-location xxD21item1 xxD21room3)		
		(item-location xxD21item2 xxD21room3)		
		(item-location xxD21item3 xxD21room2)
		(not(is-lit xxD21room1))
		(and(item-location xxD1item1 xxD1room3)		
			(item-location xxD1item2 xxD1room3)		
			(item-location xxD1item3 xxD1room2)
			(not(is-lit xxD1room1))
		)
))
)
