# MyPoker
a java poker game
- 共两个类，Poker是一个bean类，Texas类中有初始化牌、设置牌点、洗牌、显示还未发放的所有牌、发公共牌、发底牌、比较牌大小、判断牌类型的方法。
初始化牌会初始化出按顺序排列并分好花色的一副没有大小王的牌，洗牌会使所有的牌打乱顺序，发牌会从洗好的牌中随机发放，五张公共牌是游戏双方公用的，除此之外分别会发放两张底牌，双方的七张牌中随意出五张牌进行对比。

- pokers是总的牌的集合；
- publicPokers是公共牌的集合；
- myPokers是当前用户的牌的集合；
- opponentsPokers是游戏对手的牌的和。

##方法

- initPokers（） 初始化牌
- setPoint（int n，Poker poker） 设置牌点
- shufflePokers（） 洗牌
- showPokers（）  显示目前的所有牌
- dealPublicPokers（） 发放公共牌
- dealCardsInHand（） 发放两方的底牌
- compare() 比较双方的牌
