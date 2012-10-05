(define (problem shakey1)	
    (:domain shakey)
	(:objects
		xxD54321room1
		xxD54321room2
		xxD54321room3
		xxD54321box1
		xxD54321item1
		xxD54321item2		
		xxD54321item3
		xxD5431room1
		xxD5431room2
		xxD5431room3
		xxD5431box1
		xxD5431item1
		xxD5431item2		
		xxD5431item3
		xxD5421room1
		xxD5421room2
		xxD5421room3
		xxD5421box1
		xxD5421item1
		xxD5421item2		
		xxD5421item3
		xxD541room1
		xxD541room2
		xxD541room3
		xxD541box1
		xxD541item1
		xxD541item2		
		xxD541item3
		xxD5321room1
		xxD5321room2
		xxD5321room3
		xxD5321box1
		xxD5321item1
		xxD5321item2		
		xxD5321item3
		xxD531room1
		xxD531room2
		xxD531room3
		xxD531box1
		xxD531item1
		xxD531item2		
		xxD531item3
		xxD521room1
		xxD521room2
		xxD521room3
		xxD521box1
		xxD521item1
		xxD521item2		
		xxD521item3
		xxD51room1
		xxD51room2
		xxD51room3
		xxD51box1
		xxD51item1
		xxD51item2		
		xxD51item3
		xxD4321room1
		xxD4321room2
		xxD4321room3
		xxD4321box1
		xxD4321item1
		xxD4321item2		
		xxD4321item3
		xxD431room1
		xxD431room2
		xxD431room3
		xxD431box1
		xxD431item1
		xxD431item2		
		xxD431item3
		xxD421room1
		xxD421room2
		xxD421room3
		xxD421box1
		xxD421item1
		xxD421item2		
		xxD421item3
		xxD41room1
		xxD41room2
		xxD41room3
		xxD41box1
		xxD41item1
		xxD41item2		
		xxD41item3
		xxD321room1
		xxD321room2
		xxD321room3
		xxD321box1
		xxD321item1
		xxD321item2		
		xxD321item3
		xxD31room1
		xxD31room2
		xxD31room3
		xxD31box1
		xxD31item1
		xxD31item2		
		xxD31item3
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
		(ITEM xxD54321item1)
		(ITEM xxD54321item2)
		(ITEM xxD54321item3)
		(ROOM xxD54321room1) 
		(ROOM xxD54321room2)		
		(ROOM xxD54321room3)
		(DOOR xxD54321room1 xxD54321room2)
		(DOOR xxD54321room2 xxD54321room3)
		(WIDE-DOOR xxD54321room1 xxD54321room3)
		(BOX xxD54321box1)
		(box-location xxD54321room3 xxD54321box1)
		(item-location xxD54321item1 xxD54321room1)		
		(item-location xxD54321item2 xxD54321room1)		
		(item-location xxD54321item3 xxD54321room1)
		(ITEM xxD5431item1)
		(ITEM xxD5431item2)
		(ITEM xxD5431item3)
		(ROOM xxD5431room1) 
		(ROOM xxD5431room2)		
		(ROOM xxD5431room3)
		(DOOR xxD5431room1 xxD5431room2)
		(DOOR xxD5431room2 xxD5431room3)
		(WIDE-DOOR xxD5431room1 xxD5431room3)
		(BOX xxD5431box1)
		(box-location xxD5431room3 xxD5431box1)
		(item-location xxD5431item1 xxD5431room1)		
		(item-location xxD5431item2 xxD5431room1)		
		(item-location xxD5431item3 xxD5431room1)
		(ITEM xxD5421item1)
		(ITEM xxD5421item2)
		(ITEM xxD5421item3)
		(ROOM xxD5421room1) 
		(ROOM xxD5421room2)		
		(ROOM xxD5421room3)
		(DOOR xxD5421room1 xxD5421room2)
		(DOOR xxD5421room2 xxD5421room3)
		(WIDE-DOOR xxD5421room1 xxD5421room3)
		(BOX xxD5421box1)
		(box-location xxD5421room3 xxD5421box1)
		(item-location xxD5421item1 xxD5421room1)		
		(item-location xxD5421item2 xxD5421room1)		
		(item-location xxD5421item3 xxD5421room1)
		(ITEM xxD541item1)
		(ITEM xxD541item2)
		(ITEM xxD541item3)
		(ROOM xxD541room1) 
		(ROOM xxD541room2)		
		(ROOM xxD541room3)
		(DOOR xxD541room1 xxD541room2)
		(DOOR xxD541room2 xxD541room3)
		(WIDE-DOOR xxD541room1 xxD541room3)
		(BOX xxD541box1)
		(box-location xxD541room3 xxD541box1)
		(item-location xxD541item1 xxD541room1)		
		(item-location xxD541item2 xxD541room1)		
		(item-location xxD541item3 xxD541room1)
		(ITEM xxD5321item1)
		(ITEM xxD5321item2)
		(ITEM xxD5321item3)
		(ROOM xxD5321room1) 
		(ROOM xxD5321room2)		
		(ROOM xxD5321room3)
		(DOOR xxD5321room1 xxD5321room2)
		(DOOR xxD5321room2 xxD5321room3)
		(WIDE-DOOR xxD5321room1 xxD5321room3)
		(BOX xxD5321box1)
		(box-location xxD5321room3 xxD5321box1)
		(item-location xxD5321item1 xxD5321room1)		
		(item-location xxD5321item2 xxD5321room1)		
		(item-location xxD5321item3 xxD5321room1)
		(ITEM xxD531item1)
		(ITEM xxD531item2)
		(ITEM xxD531item3)
		(ROOM xxD531room1) 
		(ROOM xxD531room2)		
		(ROOM xxD531room3)
		(DOOR xxD531room1 xxD531room2)
		(DOOR xxD531room2 xxD531room3)
		(WIDE-DOOR xxD531room1 xxD531room3)
		(BOX xxD531box1)
		(box-location xxD531room3 xxD531box1)
		(item-location xxD531item1 xxD531room1)		
		(item-location xxD531item2 xxD531room1)		
		(item-location xxD531item3 xxD531room1)
		(ITEM xxD521item1)
		(ITEM xxD521item2)
		(ITEM xxD521item3)
		(ROOM xxD521room1) 
		(ROOM xxD521room2)		
		(ROOM xxD521room3)
		(DOOR xxD521room1 xxD521room2)
		(DOOR xxD521room2 xxD521room3)
		(WIDE-DOOR xxD521room1 xxD521room3)
		(BOX xxD521box1)
		(box-location xxD521room3 xxD521box1)
		(item-location xxD521item1 xxD521room1)		
		(item-location xxD521item2 xxD521room1)		
		(item-location xxD521item3 xxD521room1)
		(ITEM xxD51item1)
		(ITEM xxD51item2)
		(ITEM xxD51item3)
		(ROOM xxD51room1) 
		(ROOM xxD51room2)		
		(ROOM xxD51room3)
		(DOOR xxD51room1 xxD51room2)
		(DOOR xxD51room2 xxD51room3)
		(WIDE-DOOR xxD51room1 xxD51room3)
		(BOX xxD51box1)
		(box-location xxD51room3 xxD51box1)
		(item-location xxD51item1 xxD51room1)		
		(item-location xxD51item2 xxD51room1)		
		(item-location xxD51item3 xxD51room1)
		(ITEM xxD4321item1)
		(ITEM xxD4321item2)
		(ITEM xxD4321item3)
		(ROOM xxD4321room1) 
		(ROOM xxD4321room2)		
		(ROOM xxD4321room3)
		(DOOR xxD4321room1 xxD4321room2)
		(DOOR xxD4321room2 xxD4321room3)
		(WIDE-DOOR xxD4321room1 xxD4321room3)
		(BOX xxD4321box1)
		(box-location xxD4321room3 xxD4321box1)
		(item-location xxD4321item1 xxD4321room1)		
		(item-location xxD4321item2 xxD4321room1)		
		(item-location xxD4321item3 xxD4321room1)
		(ITEM xxD431item1)
		(ITEM xxD431item2)
		(ITEM xxD431item3)
		(ROOM xxD431room1) 
		(ROOM xxD431room2)		
		(ROOM xxD431room3)
		(DOOR xxD431room1 xxD431room2)
		(DOOR xxD431room2 xxD431room3)
		(WIDE-DOOR xxD431room1 xxD431room3)
		(BOX xxD431box1)
		(box-location xxD431room3 xxD431box1)
		(item-location xxD431item1 xxD431room1)		
		(item-location xxD431item2 xxD431room1)		
		(item-location xxD431item3 xxD431room1)
		(ITEM xxD421item1)
		(ITEM xxD421item2)
		(ITEM xxD421item3)
		(ROOM xxD421room1) 
		(ROOM xxD421room2)		
		(ROOM xxD421room3)
		(DOOR xxD421room1 xxD421room2)
		(DOOR xxD421room2 xxD421room3)
		(WIDE-DOOR xxD421room1 xxD421room3)
		(BOX xxD421box1)
		(box-location xxD421room3 xxD421box1)
		(item-location xxD421item1 xxD421room1)		
		(item-location xxD421item2 xxD421room1)		
		(item-location xxD421item3 xxD421room1)
		(ITEM xxD41item1)
		(ITEM xxD41item2)
		(ITEM xxD41item3)
		(ROOM xxD41room1) 
		(ROOM xxD41room2)		
		(ROOM xxD41room3)
		(DOOR xxD41room1 xxD41room2)
		(DOOR xxD41room2 xxD41room3)
		(WIDE-DOOR xxD41room1 xxD41room3)
		(BOX xxD41box1)
		(box-location xxD41room3 xxD41box1)
		(item-location xxD41item1 xxD41room1)		
		(item-location xxD41item2 xxD41room1)		
		(item-location xxD41item3 xxD41room1)
		(ITEM xxD321item1)
		(ITEM xxD321item2)
		(ITEM xxD321item3)
		(ROOM xxD321room1) 
		(ROOM xxD321room2)		
		(ROOM xxD321room3)
		(DOOR xxD321room1 xxD321room2)
		(DOOR xxD321room2 xxD321room3)
		(WIDE-DOOR xxD321room1 xxD321room3)
		(BOX xxD321box1)
		(box-location xxD321room3 xxD321box1)
		(item-location xxD321item1 xxD321room1)		
		(item-location xxD321item2 xxD321room1)		
		(item-location xxD321item3 xxD321room1)
		(ITEM xxD31item1)
		(ITEM xxD31item2)
		(ITEM xxD31item3)
		(ROOM xxD31room1) 
		(ROOM xxD31room2)		
		(ROOM xxD31room3)
		(DOOR xxD31room1 xxD31room2)
		(DOOR xxD31room2 xxD31room3)
		(WIDE-DOOR xxD31room1 xxD31room3)
		(BOX xxD31box1)
		(box-location xxD31room3 xxD31box1)
		(item-location xxD31item1 xxD31room1)		
		(item-location xxD31item2 xxD31room1)		
		(item-location xxD31item3 xxD31room1)
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
		(DOOR xxD1room1 xxD31room1)
		(DOOR xxD31room1 xxD321room1)
		(DOOR xxD31room1 xxD41room1)
		(DOOR xxD41room1 xxD421room1)
		(DOOR xxD41room1 xxD431room1)
		(DOOR xxD431room1 xxD4321room1)
		(DOOR xxD431room1 xxD51room1)
		(DOOR xxD51room1 xxD521room1)
		(DOOR xxD51room1 xxD531room1)
		(DOOR xxD531room1 xxD5321room1)
		(DOOR xxD531room1 xxD541room1)
		(DOOR xxD541room1 xxD5421room1)
		(DOOR xxD541room1 xxD5431room1)
		(DOOR xxD5431room1 xxD54321room1)


		(GRIP grip-left)
		(GRIP grip-right)
		(in-room xxD54321room1))
	(:goal 
		(and(item-location xxD54321item1 xxD54321room3)		
			(item-location xxD54321item2 xxD54321room3)		
			(item-location xxD54321item3 xxD54321room2)
			(not(is-lit xxD54321room1))
		(and(item-location xxD5431item1 xxD5431room3)		
			(item-location xxD5431item2 xxD5431room3)		
			(item-location xxD5431item3 xxD5431room2)
			(not(is-lit xxD5431room1))
		)
		(and(item-location xxD541item1 xxD541room3)		
			(item-location xxD541item2 xxD541room3)		
			(item-location xxD541item3 xxD541room2)
			(not(is-lit xxD541room1))
		)
		(and(item-location xxD51item1 xxD51room3)		
			(item-location xxD51item2 xxD51room3)		
			(item-location xxD51item3 xxD51room2)
			(not(is-lit xxD51room1))
		)
		(and(item-location xxD4321item1 xxD4321room3)		
			(item-location xxD4321item2 xxD4321room3)		
			(item-location xxD4321item3 xxD4321room2)
			(not(is-lit xxD4321room1))
		(and(item-location xxD431item1 xxD431room3)		
			(item-location xxD431item2 xxD431room3)		
			(item-location xxD431item3 xxD431room2)
			(not(is-lit xxD431room1))
		)
		(and(item-location xxD41item1 xxD41room3)		
			(item-location xxD41item2 xxD41room3)		
			(item-location xxD41item3 xxD41room2)
			(not(is-lit xxD41room1))
		)
		(and(item-location xxD1item1 xxD1room3)		
			(item-location xxD1item2 xxD1room3)		
			(item-location xxD1item3 xxD1room2)
			(not(is-lit xxD1room1))
		)


)))
)
