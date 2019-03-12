## frog | 人工生命 
**License:** [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)  

这是一个人工生命试验项目，最终目标是创建“有自我意识表现”的模拟生命体，技术架构基于02年提出的 [一个人工脑模型](一个人工脑模型.md)。
这个项目永远没有结束的时候，开始于模拟一个简单的生命体，然后是青蛙、狗......, 结束于有“自我意识表现”的人工脑，或者说，结束于被机器人代替人类的那一天。

## 缘起 | Origin
目前人工智能的进展已经比较完美地解决了模式识别这块的难题，人脸识别、语音识别已经不弱于人类的水平，而这是我在二十年前感到最困惑的一块，因为底子差和当时的电脑速度慢。模式识别解决了，剩下的问题就简单多了，目前距离人工意识的诞生只差临门一脚了，就是如何在识别的基础上“理解”这些识别的内容并与人类形成交互的反馈。所以这个项目的重点不在于模式识别，而是在利用模式识别的成果基础上，训练神经网络形成条件反射，表现出高等动物才具有的条形反射行为，最终表现为"拥有自我意识"的行为。根据“意识不是一种存在，而是一种现象”原理，如果最终一个系统表现出具有自我意识的行为，即可认为它也是人，应该获得人权。目前有些人工智能的研究目的是想让人工智能解决一些复杂的人类社会方面的问题如机器翻译等，则是完全错误的目标，不可能成功，因为如果一个系统不能表现出自我意识，它就不能与人类交流，也就不可能具有解决这些问题的能力，表现出来的现象就是通常说的"机器不犯错，一旦犯错就是大错"。另一方面，如果一个系统表现出具有自我意识的行为，它就完全有能力解决世界上所有难题，包括改进它的自身和淘汰人类(因为他是先进生产力的代表）。所以人工智能的研究重点应该放在人工生命的构建和论理研究，而不是期待短期收益，指望人类可以一直享受人工智能的大餐是很危险的。模式识别和深度学习的成果只是人工生命的踮脚石和一块路标而已。人工智能的“有用”的应用，很可能只是短暂的一段过渡期而已，不用高兴得太早，也许都是白忙，给机器人作嫁衣而已，当然，深度学习的成果在将来还是有用的，生物体从来不能象计算机那样对某个技能反复训练上千万次，达到史无前例的覆盖度，既使在机器人时代，也是一个有用的技术。  
简单来说，这个项目的目的是试验和探索神经网络开发的另一个方向，即以实验为导向，模拟生命进化的过程，按照优胜夯汰、随机变异、用进废退这三大原则,一步一步地搭建出从低等到复杂的人工生命体，除了模式识别的成果可以部分引用，原则上不需要很多的数学知识，因为它是由实验为驱动，而不是由算法着手来搭建神经网络。

从单细胞进化到多细胞、从青蛙进化到人类，这是一个漫长的、随机的进化过程，但在超级电脑上跑可能只要几天时间，就可能得到一个相当不错的脑模型。当然电脑速度越快、容量越大、环境模拟的越真实，则优胜夯汰后形成的脑结构就越复杂，错的脑模型都被自然淘汰掉了。从算法着手搭建，还是从模拟环境着手自动进化，这是创建人工生命的两个大方向，第一个方向有可能走到死胡同里，因为它不具备算法自改进、变异、遗传(算法的压缩)功能，当脑模型复杂到一定地步，会超出人脑能理解的范畴。模拟环境方式的难点则在于环境本身必须足够复杂、正确。而且必须循序渐进，与脑的进化同步，如果把一群青蛙扔到猴子的模拟环境中，则所有青蛙都会被自然淘汰掉，项目就无法进行下去了，另一个困难是电脑必须非常快，因为它是用串行方式模拟并行，不断试错前进的过程。   目前的项目只是搭建了一个框架，语言为Java，利用Swing作图环境，构建一个500x500象素点的虚拟环境、并模拟一群草履虫的优胜夯汰，来获取一个具备自进化功能的人工生命体，具体脑(即电脑生成的神经网络)的生成、进化算法还需要以后逐渐加入。欢迎有对神经网络感兴趣的同学加入这个实验项目，大家一起来玩，这个项目不需要多少数学知识，重在实践。  

## 短期目标 | Sort-term Goals  
目前它的第一个初步目标是：造出一个真正意义上的人工生命：草履虫。它必须具备以下前四个特点：   
* 脑结构由电脑生成：神经网络由电脑算法生成，但是电脑算法仅限于模拟环境，而不是直接参与搭建神经网络，就好象大自然只负责拍死不合格的生命，它从不主动参与设计大脑。  
* 脑结构可遗传：类似于生物的DNA，电脑生成的脑结构(神经网络)，可通过简单的算法规则描述，并且此算法规则可以压缩成较短的片段存储，并参与到下一代草履虫的构建。  
* 脑结构可变异：算法规则可以变异，下一代生成的草履虫在脑结构上与上一代总体相似，但存在部分变异。  
* 适应环境：草履虫能够在模拟的虚拟环境下存活下来，环境有微小的变化，能够自适应环境，并一代代生存将适应这种环境的能力遗传下来。  
* 用进废退：这是一个假想，对于生物来说，存在这样一种现象，就是用的多的器官，容易发生变异（例如经常嚼槟榔，容易发生口腔癌变）,有理由相信这不是偶然现象，而是生物在进化过程中的一个有用的功能，以便于更快地变异，以适应环境，并很可能这种变异会通过遗传细胞(变异的算法规则)影响到下一代。  

## 理论 | Theory  
为什么明明是个电脑程序，只要满足上述前四个特点就可以称之为"真正"的人工生命? 这一点我不想多说，大家可以百度一下"zhangrex 人工生命"就知道我的观点了：意识从来就不存在，意识只是一种现象。风吹、树动和风吹、添衣，都只是现象而已，意识本质上是一种现象，换句话说，只要表现出生命现象的事物，就可以称其为生命了，不管它是高等还是低等，不管它的物质存在基础是怎样的。二十年前我就开始思考这个问题，提出了“我思我不在”的口号，请仔细考虑一下这个观点，哲学上、理论上对智能、意识的研究是很无聊的，相当于在研究“无”到底是什么，不必多纠缠在理论和算法上。是的，用模拟环境得到的人工神经网络模型，我们确实无法掌控它的算法是怎样生成的，但是我们知道，这符合大自然创造生命的规律。

## 项目架构 | Architecture 
这是一个Java项目，分为Application、Env、Frog三个模块: 
Application模块: 用于项目的启动、关闭等基础服务，Windows环境下可以用run.bat来启动它查看演示。  
Env模块: 目前是一个500x500象素点的虚拟空间，模拟一个生物生存区，用不同形状的图形点阵来表达和模拟食物、天敌、障碍等物体，这个虚拟空间由程序员全权控制，将随着Frog的脑进化而不断变得越来越复杂。  
Frog: 这是人工生命的主体，目前起名叫青蛙(Frog)，其实叫什么都一样。它主要具备以下器官：
* 运动器官: 与运动神经元相连，只有五个动作：上下左右停。
* 进食器官：当Frog与食物的坐标重合时，食物会被从Env中删除，并相应增加Frog的能量值，并激活Frog的进食感觉神经元，随时间流逝能量将减少，能量耗尽则Frog死亡。  
* 视觉器官: 这是脑模型的一部分，在实验中先固定随意取脑中心位置(0,0)点附近的神经元区作为视觉区。    
* 脑器官: 这即是程序员要解决的问题，也是我们要获取的最终目标。脑模型的生成由电脑优胜夯汰、循环迭代进化生成，但这个进化的算法还是必须由程序员来掌控，一步步探索出来，即要防止虚拟环境太复杂，也要避免脑模型不适应环境，生命体全部被淘汰，导致实验中断，无法进行下去。     
	
## 技术细节和构思
* 通过数组来模拟神经网络，用串行的循环来模拟并行芯片运作方式。用Frog的能量多少来衡量是否将它淘汰还是允许它产生后代(下蛋)参与下一轮的测试，因为这个项目的目的是获取智能体，与一般的生命游戏还是有区别的，并不是适者生存就结束了，而是必须完成一系列程序员设定好的目标，一步步进化，直到表现出自我意识现象为止。  
* 脑模型的生成算法通过简单的神经元连接完成，原则上不允行出现任何形式的硬编码(除模式识别外)，因为硬编码可能会破坏“随机变异”这一生命特性。
* 为简单起见，暂不考虑引入GPU图形芯片进行加速, 欢迎能接触到超级计算机的同学加入，随着虚拟环境的复杂度和神经元数量增多，对电脑速度要求会越来越高。  
* 暂选用Java语言实现  
* 更多的杂七八拉的一些想法和构思放在“开发思路.md”、“一个人工脑模型.md”等文里。  

## 项目要实现的短期和长远目标  
* 脑模型和虚拟环境的初步搭建 [脑模型刚开始搭建。虚拟环境已完成，点击run.bat可以查看演示]
* 使脑模型具有视觉功能,如果有食物在它附近，将激发天生条件反射，向食物移动，并获得进食奖励 [未完成]
* 引入现成的图像识别算法，使脑模型具有图像识别功能，根据形状区分食物、毒物、天敌 [未完成]
* 如果误食有毒食物，将激发天生条件反射，获得惩罚并扣除能量，天生痛觉区兴奋。[未完成]
* 如果被天敌攻击，将激发天生条件反射，获得惩罚并扣除能量,天生痛觉区强烈兴奋。[未完成]
* 训练它将打击行为与痛觉兴奋区发生关联。[未完成]
* 训练它将看到“打”这个文字，与打击行为和痛觉兴奋区建立即系。[未完成]
* 训练它背下这100个字中所有汉字可能组成的常用词组，给它一个字，所有与这个字相关的词组细胞区兴奋。[未完成]
* 训练它一看到“食物来了”文字，就从窝里出来找吃的。[未完成]
* 训练它理解“你”，“我”、“他”文字，只针对“我”相关的指令作出反应。[未完成]
* 训练它认识数字，会做四则运算[未完成]
* 训练它认识圆、矩形、会计算面积,学会估算和判断"大"和"小"[未完成]
* 训练它认识坐标和时间，并按指令行动，如看到"你在9点走到右上角去，等三分钟后再回来"，将遵从指令行动。[未完成]

## 最终目标
* 扩大它的输入网格和输出网格规模，扩大神经元数量，
* 移殖到超级电脑上，由人来同它交流，输入新的图形和汉字，纠正它说的错误的话  
* 移殖到并行芯片硬件上  

## 目前进展和成绩
2018.10.21 开始编码。  
2019.03.11 虚拟环境已建好，可以模拟低等生命的遗传、繁殖、变异、进化现象，但目前只能往一个方向运动，相当于一个最简单的单细胞生物，还不具备视觉能力，不具备主动找食能力。    
运行run.bat可以查看演示。Env.java中的几个重要参数说明:  
* SHOW_SPEED： 调整实验的速度(1~1000)，值越小则越慢。  
* EGG_QTY: 每次允许Frog下多少个蛋，每个蛋可以孵出4个青蛙。通常下蛋取值在10~1000之间。蛋保存着我们测试的结果。实验的最终目标就是获得一个蛋。  
* FOOD_QTY：食物的数量，食物越多，则Frog的生存率就越高，能量排名靠前的一批Frog可以下蛋，其余的被淘汰。  
下面是这个测试的动画截图，有兴趣的可以试着自己运行一下:  
![截图](https://gitee.com/drinkjava2/frog/raw/master/result.gif)
另外每步演示的结果(egg)会存盘在根目根目录下，名为egg.ser，可以删除这个文件以从头开始新的测试。目前万里长征刚踏上第一步，因为还没涉及脑模型的搭建。可以看到有些青蛙跑得飞快，这是自然选择的结果，因为跑在最前面的吃得多。以后会改正这个bug，要让最聪明的、会抢食的Frog胜出，而不是让跑得快的胜出。  

## 版权 | License  
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)  

## 期望 | Futures
欢迎发issue提出更好的意见或加入开发组，一起来玩撸青蛙   

## 作者其它开源项目 | Other Projects
- [Java持久层工具 jSqlBox](https://gitee.com/drinkjava2/jSqlBox)   
- [微型IOC/AOP工具 jBeanBox](https://gitee.com/drinkjava2/jBeanBox)  
- [服务端页面布局工具 jWebBox](https://gitee.com/drinkjava2/jWebBox)  
- [前端写后端SQL工具 GoSqlGo](https://gitee.com/drinkjava2/gosqlgo)   
- [MyBatis增强插件 MyFat](https://gitee.com/drinkjava2/myfat)   

## 关注我 | About Me
[Github](https://github.com/drinkjava2)  
[码云](https://gitee.com/drinkjava2)  
