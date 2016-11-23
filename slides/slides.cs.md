## Scala pro začátečníky
Michal Šenkýř - Sklik vývojář



## Úvod
- Scala je čistě object-oriented
  - Vše je objekt (včetně traits a primitivních typů)
- Scala je funkcionální
  - Vše je výraz (včetně `if`, `for`, ...)
- Scala je úspěšně používána v produkci u velkých společností
  - Twitter, Netflix, Tumblr, LinkedIn, Foursquare, ...

Note:
trait je Scala ekvivalent interface



## Scala & Java
- Scala běží na Java Virtual Machine
  - Zaběhnutá stabilní platforma
- Scala si "rozumí" s Javou
  - Výsledný bytecode stejný
  - Všechny třídy lze použít bez větších problémů – Scala třídy ve speciální knihovně
  - Podpora psaní kódu takovým způsobem, aby se výsledek jevil jako Java (anotace pro getter/setter konvenci, konvertory na Java kolekce, atd.)



## Scala vs Java
Java
```java
public class Person implements Serializable {
    private final String firstName;
    private final String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Person withFirstName(String firstName) {
        return new Person(firstName, lastName);
    }

    public Person withLastName(String lastName) {
        return new Person(firstName, lastName);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return Objects.equals(firstName, o.firstName) &&
            Objects.equals(lastName, o.lastName);
    }

    public int hashCode() {
        Objects.hash(firstName, lastName);
    }

    public String toString() {
        return "Person(" + firstName + "," + lastName + ")";
    }
}
```

Scala
```scala
case class Person(firstName: String, lastName: String)
```



## Scala vs Java
Java
```java
List<String> keywords = Arrays.asList("Apple", "Ananas", "Mango", "Banana", "Beer");
Map<Character, List<String>> result = new HashMap<>();
for (String k : keywords) {
    char firstChar = k.charAt(0);
    if (!result.containsKey(firstChar)) {
        result.put(firstChar, new ArrayList<>());
    }
    result.get(firstChar).add(k);
}
for (List<String> list : result.values()) {
    Collections.sort(list);
}
System.out.println(result);
```
Scala
```scala
val keywords = List("Apple", "Ananas", "Mango", "Banana", "Beer")
val result = keywords.sorted.groupBy(_.head)
println(result)
```

Note:
Mapa seznamů slov podle prvních písmen s lexikografickým řazením



## Scala vs Java
- Podpora funkcionálního programování
  - Už od roku 2004, Java 8 až o 10 let později
  - Plná podpora closures
- Propracovanější Collections knihovna
- Pattern matching
- Inference konce výrazu (žádné středníky!)
- Klade silný důraz na používání immutable tříd
  - Lepší paralelizace operací



## Scala vs Java
- Vyšší informační hustota
  - Odstranění zbytečné syntaxe, inference typů, string interpolace, ...
  - Definice vlastních operátorů
- Důraz na čitelnost kódu
- Důraz na dobrou udržovatelnost kódu
  - Převod nejčastějších runtime problémů na compile-time chyby
  - Silná závislost na typových parametrech, nahrazení null hodnot třídou Option, používání immutable tříd

Note:
Plná podpora closures = lze měnit vnější proměnné



## Scala way vs Java way
- Ultimátním cílem je minimalizace kódu a čitelnost
  - Oproti slepému používání design patternů
- Nepoužívat kanón na vrabce
  - Obvykle je snazší refaktorovat
- Scala nedrží vývojáře za ručičku
  - Poskytuje nástroje – jejich správná volba je na programátorovi
- Preference immutable tříd
  - Funkcionální programování, paralelizace, testování
- Zachovávání maximální type safety
  - Minimalizace přetypovávání, TypeTags pro předávání informace o typových parametrech za runtime

Note:
Nedrží za ručičku = poskytuje velké množství různých cest, jak dosáhnout daného cíle
Type Tags – Scala umí automaticky resolvit třídy v typových parametrech, pokud je to na patřičných místech aktivováno



## Hierarchie tříd
![Hierarchie tříd](img/classhierarchy.png) <!-- .element: style="border: none; background: none; box-shadow: none" -->



## SBT – Simple Build Tool
- http://scala-sbt.org
- Implicitní podpora Javy a Scaly
- Automaticky stáhne Scala kompilátor a knihovny
- Používá Scala kompilátor
- Snadno rozšiřitelný
- Umí používat Maven repozitáře přes Ivy
- Scala REPL dostupný přes: sbt console



## Příprava
- Vytvoření projektu
  ```bash
  mkdir -p projekt/src/main/scala && cd projekt
  ```
- build.sbt
  ```scala
  name := "Projekt"
  
  scalaVersion := "2.12.0"
  ```
- Kompilace a spuštění
  - `sbt compile` – pouze kompilace
  - `sbt run` – kompilace a spuštění
  - `sbt ~run` – průběžná kompilace a spouštění

Note:
JRE >= 1.6
Download SBT:
  curl -L -o sbt https://raw.githubusercontent.com/paulp/sbt-extras/master/sbt && chmod +x sbt
Download Ammonite REPL:
  curl -L -o amm https://git.io/vP4Gw && chmod +x amm

Další SBT direktivy:
organization = groupId
version = verze projektu
description = popis
libraryDependencies = závislosti
publishMavenStyle = vygenerovat POM



## Immutable proměnné
```scala
val x: Int = 5
```
- Lze vynechat typ – použije se typová inference
- Stejné jako definování `final` proměnné v Javě, ale kratší
- Výhody pro paralelizaci
- Výhody pro čitelnost kódu
- Lazy evaluation
  ```scala
  lazy val x: Int = 5
  ```

Note:
Java JIT kompilátor umí odvodit podle použití v kódu, zda lze optimalizovat jako final



## Mutable proměnné
```scala
var x: Int = 5
```
- Vhodné pro výkonostně kritický kód
- Obtížnější úvaha o obsažené hodnotě
- Použití doporučováno pouze v případě, že “víte, co děláte”



## Definice tříd
```scala
class Test
```
- Lze definovat více tříd v jednom souboru
  - Není potřeba, aby se soubor jmenoval stejně jako třída
  - Přehlednější souborová struktura
- Modifikátory
  - Jako v Javě, přičemž default je public a package private lze specifikovat na jakýkoliv superpackage
    ```scala
    private[sql]
    ```



## Vestavěné singletony
```scala
object Test
```
- To samé jako Java třída se všemi static membery
  - Scala třídy nemohou mít static membery – statická funkcionalita se píše do tzv. companion objektů (singleton se stejným názvem)
- Lze simulovat package hierarchii

Note:
Více tříd – např. Service a Exception



## Primární konstruktor
```scala
class Test(param: Int,
           val get: Int,
           var getSet: Int,
           private val privateGet: Int) {
  ...
}
```
- Píše se přímo do těla třídy, parametry do signatury třídy
- Parametrům konstruktoru se vytvoří gettery/settery, pokud jsou předcházeny klíčovým slovem `val`/`var`
- Lze určit default hodnoty parametrů
- Všem definovaným proměnným se automaticky vytváří gettery/settery
  - lze obejít použitím bloku



## Doplňkové konstruktory
```scala
def this(param: String) = this(param.toInt)
```
- Píší se jako this metody bez uvedeného návratového typu
- Musí začínat voláním jiného existujícího konstruktoru
  - Řetěz vždy končí voláním primárního konstruktoru



## Metody
```scala
def transform(param1: Int, param2: Int = 2): Int = param1 * param2
```
- Návratový typ lze vynechat, pokud lze odvodit typovou inferencí
- Lze určit default hodnoty parametrů
  - Při volání lze určit parametr názvem místo pořadím
    ```scala
    transform(param1 = 1)
    ```
- Metodám bez parametrů lze vynechat závorky
  - Závorky obvykle značí metody se side-effecty
- Parametr lze předat jménem (lazy vyhodnocování)
  ```scala
  def tranform(param: => Int): Int = ...
  ```

Note:
Metody bez parametrů se tváří jako fieldy
Závorky u side-effectů se používají, aby se metoda nepletla s fieldem
Zmínit rozdíly ve volání metod bez parametrů bez/s závorkami (závorky lze vynechat, ale ne přidat)



## Metody
- Podporují varargs parametry
  ```scala
  def transform(params: Int*): Int = ...
  ```
  - Uvnitř těla metody se s nimi nakládá jako se `Seq[T]`
  - Pro rozvinutí `Seq[T]` na varargs při volání je nutné explicitně zkonvertovat
    ```scala
    transform(paramsSeq: _*)
    ```
- Metody a funkce nejsou totéž
  - Lze zkonvertovat metodu na funkci
    ```scala
    val function = method _
    ```



## Symbolické metody (operátory)
- Všechny metody lze volat v infixovém/postfixovém stylu
  ```scala
  class Test(val x: Int) {
    def +(other: Test) = new Test(x + other.x)
    def ! = new Test(-x)
  }
  new Test(1) + new Test(2) // == new Test(3)
  new Test(1) !             // == new Test(-1)
  ```
  - Pořadí v infix stylu je obrácené, pokud název metody končí dvojtečkou
  - Znakové infix operátory (+, -, *, /, atd.) se řídí typickou precedencí



## Typové parametry
```scala
List[String]
```
- Interně se s nimi nakládá stejně jako s Java generikami (erasure)
- Kompilátor o nich má kompletní přehled a vynucuje si jejich dodržování
  - Jejich vynechávání/přetypovávání se silně nedoporučuje
- Existují techniky, jakými lze typové parametry předat do runtime

Note:
Postfix hlásí warning, pokud se nenaimportuje language.postfixOps



## Speciální metody
- `equals`
  - Volá se při použití operátoru `==`
- `apply`
  - Lze vynechat její volání a “volat” přímo objekt/instanci (snadnější interoperace OOP - FP)
  - Přehlednost při použití objektů `FunctionN`, `TupleN`, atd.
- `update`
  - Totéž jako `apply`, ale s následujícím přiřazením



## Speciální metody
- `..._=`
  - Nahrazují settery, používají se jako přiřazení do fieldu
  - Getter musí existovat
- `unary_+`, `unary_-`, `unary_!`, `unary_~`
  - Definují korespondující unární operace



## Speciální metody
- `unapply`
  - Vrací `Option[T]`
  - Používá se v pattern matchingu pro extrakci parametrů (inverz k `apply`)
    - Pro více parametrů je `T` typu `TupleN`
  - Lze použít při přiřazování
    ```scala
    val Person(name, email) = person
    ```
- `unapplySeq`
  - Vrací `Option[Seq[T]]`
  - Jako `unapply`, ale pro proměnlivý počet parametrů
  - Např. pro regex pattern matching

Note:
Ukázat vnořený unapply (s tuples)



## Case třídy
- Syntactic sugar pro value třídy
- Všechny parametry jsou fieldy s public gettery
- `copy` s default hodnotami pro možnost vytvoření kopie se změněnými vybranými fieldy
- `equals`, `hashCode` a `toString` podle parametrů
- Extendují `Product`
  - umožňuje přístup k parametrům jako k seznamu hodnot
- Navíc se vytváří companion objekt
  - `apply` pro možnost vynechání `new`
  - `unapply` jako extraktor parametrů konstruktoru



## Tuples
```scala
val tuple: (Int, Int) = (1, 2)
```
- Alternativní syntaxe:
  ```scala
  val tuple: (Int, Int) = 1 -> 2
  ```
- Odpovídají typům `Tuple1[T1]`, `Tuple2[T1, T2]`, ...
- Symbolizují typově bezpečnou N-tici prvků (pro TupleN)
- Obsahují `apply`, `unapply`, `_N`, `hashCode`, `equals`, `swap`, atd.



## Funkce
```scala
val function: Int => String = (x: Int) => x.toString
```
- Odpovídají typům `Function0[+R]`, `Function1[-T1, +R]`, ...
- Lze vynechat typy parametrů, pokud jsou odvoditelné typovou inferencí:
  ```scala
  val function: Int => String = x => x.toString
  ```
- Symbolizují funkce s N parametry (pro `FunctionN`)
- Obsahují `apply`, `curried`, `tupled`



## Parciální funkce
```scala
val f = new PartialFunction[Int, Double] {
  override def apply(x: Int) = 1 / x
  override def isDefinedAt(x: Int) = x != 0
}
```
- Odpovídá typu PartialFunction[-A, +B]
- Symbolizuje funkci s jedním parametrem s omezeným definičním oborem
- Lze definovat snadněji pomocí [pattern matchingu](#/pattern-matching)
- Obsahuje `isDefinedAt`, `orElse`, `andThen`, `lift`, `applyOrElse`, atd.

Note:
Ukázat příklad definice funkce, kde parametry nejsou odvoditelné typovou inferencí:
val function = x => x.toString



## Importing
- Klasickou syntaxí lze importovat třídy/objekty
  - Wildcard je podtržítko
  - Statické importy se neznačí explicitně
    ```scala
    import scala.collection.JavaConverters._
    ```
- Lze importovat více vybraných tříd/objektů najednou
  ```scala
  import scala.io.{Codec, Source}
  ```
- Lze importovat z vnořených objektů jako by to byly packages



## Importing
- Importy se mohou řetězit
  ```scala
  import scala.collection
  import collection.Map
  ```
- Libovolnou třídu/objekt lze přejmenovat
  ```scala
  import java.util.{List => JavaList}
  ```
- Lze vynechat třídy/objekty z wildcard importu
  ```scala
  import java.util.{Map => _, _}
  ```
- Importy lze definovat i lokálně v libovolném bloku
  - Typicky použito například při importu memberů z companion objektu



## Pattern matching <!-- .slide: id="pattern-matching" -->
```scala
x match {
  case ... =>
    ...
}
```
- Vše za `=>` interpretováno jako blok až do dalšího casu nebo konce bloku
- Kompilátor kontroluje, zda je seznam casů vyčerpávající
- Porovnání konkrétní hodnoty
  ```scala
  case "value" =>
  ```
- Default case
  ```scala
  case _ =>
  ```



## Pattern matching
- Porovnání s hodnotou uloženou v lokální proměnné
  ```scala
  case `variable` =>
  ```
- Porovnání typu
  ```scala
  case str: String =>
  ```
- Podpora podmínek
  ```scala
  case person if person.name.startsWith("Jan") =>
  ```



## Pattern matching
- Extrakce proměnných přes unapply/unapplySeq
  ```scala
  case Person(name, email) =>
  ```
- Extrakci lze vnořovat
  ```scala
  case Person(name, Address(city, street)) =>
  ```
- Lze extrahovat pouze vybrané proměnné
  ```scala
  case Person(_, Address(city, _)) =>
  ```
- Binding celé hodnoty
  ```scala
  case person@Person(name, _) =>
  ```



## Pattern matching
- Používá se při odchytávání výjimek
  ```scala
  val input = try System.console.readLine() catch {
    case _: IOError =>
      println("Nelze číst ze vstupu")
      "Default"
  } finally println("Konec vstupu")
  ```
- Samotný blok casů funguje jako zkrácený zápis pro PartialFunction
  ```scala
  val partFunc: PartialFunction[Any, Unit] = {
    case s: String => println(s)
  }
  ```



## Úvod do kolekcí
- Scala obsahuje velmi silný framework pro práci s kolekcemi pomocí funkcí vyššího řádu
- Transformace celých kolekcí místo práce s jednotlivými prvky
- Připomíná moderní Java Streams API, ale mnohem obsáhlejší (a prověřenější)
- Důraz na výkon i na maximální čitelnost kódu
- Důraz na obecnost – metody jsou definovány co nejvýše v hierarchii tříd
  - `String` i `Array` jsou součástí hierarchie (lze na nich volat stejné operace jako na sekvencích)



## Úvod do kolekcí
- Člení kolekce na immutable a mutable, kde immutable jsou silně preferované
- Stále existují obecné nadtřídy/traits – lze se spolehnout na to, že kód, který je používá, přijímá immutable i mutable kolekce a neprovádí v nich žádné změny
- Doporučuje se importovat jen balík `scala.collection.mutable` a přistupovat přes něj:
  ```scala
  val set = new mutable.HashSet[Int, String]
  ```
- Nejužívanější kolekce mají alias ve `scala._` a není tedy potřeba je importovat

Note:
Prověřenější = od roku 2010 (Java Stream od 2014)



#### [Immutable kolekce](http://docs.scala-lang.org/overviews/collections/overview.html)

![Immutable kolekce](img/collections.immutable.png) <!-- .element: style="border: none; background: none; box-shadow: none; max-width: 80%" -->

Note:
Option není kolekce, ale lze k němu tak přistupovat



#### [Mutable kolekce](http://docs.scala-lang.org/overviews/collections/overview.html)

![Mutable kolekce](img/collections.mutable.png) <!-- .element: style="border: none; background: none; box-shadow: none; max-width: 95%" -->



## Konstrukce kolekcí
- Všechny kolekce lze snadno sestavit pomocí metody `apply`
  ```scala
  List("a", "b")
  Array("a", "b")
  Set("a", "b")
  TreeMap("a" -> 1, "b" -> 2)
  mutable.HashSet("a", "b")
  ```
- Prázdné kolekce lze získat pomocí metody `empty`
  ```scala
  List.empty
  Set.empty
  Array.empty
  ```
  - Typy jsou doplněny podle použití pomocí inference, ale lze je i explicitně uvést



## Konstrukce kolekcí
- Mnoho dalších užitečných konstrukčních metod na companion objektech
  ```scala
  // Celá čísla dělitelná třemi do sta
  Array.range(0, 100, 3)
  // Malá násobilka
  Array.tabulate(10, 10) { (x, y) => (x + 1) * (y + 1) }
  // Prvních deset mocnin Eulerova čísla
  Array.iterate(1.0, 10)(_ * Math.E)
  // Obsahy kruhů o poloměrech 1 až 10
  Array.tabulate(10)(Math.PI * Math.pow(_, 2))
  ```
- Uniformní rovnost
  - Rozlišuje množiny, mapy a sekvence
  - Nerozlišuje mutable/immutable



## Základní operace
Operace                                    | Popis
-------------------------------------------|----------------------------
`xs(i)`                                    | Vrátí prvek na i-té pozici
`xs.size`                                  | Počet prvků v kolekci
`xs.isEmpty`, `xs.nonEmpty`                | Zda je/není kolekce prázdná
`xs.indexOf(x)`                            | Index prvku x
`xs.hasDefiniteSize`                       | Zda je kolekce velikostně omezená
`xs.head`, `xs.last`                       | První/poslední prvek


Operace                                    | Popis
-------------------------------------------|----------------------------
`xs.headOption`, `xs.lastOption`           | První/poslední prvek jako `Option[T]`
`xs.tail`, `xs.init`                       | Kolekce bez prvního/posledního prvku
`xs.find(p)`                               | Najde první prvek podle daného predikátu
`xs.forall(p)`                             | Zda pro všechny prvky platí daný predikát
`xs.exists(p)`                             | Zda existuje prvek splňující predikát


Operace                                    | Popis
-------------------------------------------|----------------------------
`xs.sum`, `xs.product`                     | Sečte/vynásobí numerické prvky v kolekci
`xs.min`, `xs.max`                         | Minimální/maximální řaditelný prvek
`xs.mkString(start,sep,end)`               | Vytvoří `String` všech prvků s daným prefixem, suffixem a separátorem
`xs.toArray`, `xs.toList`, `xs.toSeq`, ... | Zkonvertuje kolekci na kolekci daného typu


Operace                                    | Popis
-------------------------------------------|----------------------------
`xs.toMap`                                 | Vytvoří mapu z kolekce dvojic (`Tuple2`)
`xs.sameElements(ys)`                      | Zda kolekce obsahují stejné prvky
`xs.foreach(f)`                            | Provede danou funkci na každém prvku kolekce
`xs.transform(f)`                          | Transformuje každý prvek mutable kolekce danou funkcí
`ms.transform(f)`                          | Transformuje všechny hodnoty v mapě funkcí f



## Transformační operace
Operace    | Popis
-----------|------------------------------------
`xs ++ ys` | Konkatenuje dvě kolekce
`xs -- ys` | Odstraní prvky podle druhé kolekce
`xs + x`   | Kolekce s přidaným/odebraným prvkem
`xs - y`   | Kolekce s přidaným/odebraným prvkem
`xs & ys`  | Průnik množin
`xs ⎮ ys`  | Sjednocení množin


Operace                | Popis
-----------------------|------------------------------------------------
`xs.take(n)`           | Prvních n prvků kolekce
`xs.drop(n)`           | Kolekce bez prvních n prvků
`xs.takeWhile(p)`      | Nejdelší prefix kolekce, pro nějž všechny prvky splňují predikát
`xs.dropWhile(p)`      | Odstraní nejdelší prefix kolekce podle predikátu
`xs.patch(i,ys,r)`     | Kolekce vzniklá nahrazením r prvků od i-tého kolekcí ys


Operace                | Popis
-----------------------|------------------------------------------------
`xs.filter(p)`         | Kolekce pouze těch prvků, které splňují daný predikát
`xs.map(f)`            | Transformuje každý prvek kolekce danou funkcí
`xs.flatMap(f)`        | Transformuje každý prvek na kolekci danou funkcí a konkatenuje výsledek
`xs.collect(f)`        | Aplikuje danou parciální funkci na každý prvek a shromáždí výsledky


Operace                | Popis
-----------------------|------------------------------------------------
`xs.zip(ys)`           | Kolekce párů prvků z daných kolekcí se stejným indexem
`xs.zipAll(ys,x,y)`    | Jako zip, ale kratší kolekce je doplněna o prvky x/y
`xs.zipWithIndex`      | Kolekce párů prvku a jeho indexu


Operace                | Popis
-----------------------|------------------------------------------------
`xs.partition(p)`      | Rozdělí kolekci na dvě podle daného predikátu
`xs.groupBy(f)`        | Rozdělí kolekci do mapy kolekcí podle daného diskriminátoru
`xs.sortBy(f)`         | Kolekce prvků seřazených podle daného diskriminátoru



## Další operace
Operace                                 | Popis
----------------------------------------|------------------------------
`(z /: xs)(op)`, `xs.foldLeft(z)(op)`   | Aplikuje danou binární operaci postupně na mezivýsledek a každý prvek kolekce postupně zleva doprava, kde z je iniciální hodnota
`(xs :\ z)(op)`, `xs.foldRight(z)(op)`  | Jako `foldLeft`, ale zprava


Operace                                   | Popis
------------------------------------------|------------------------------
`xs.reduceLeft(op)`, `xs.reduceRight(op)` | Jako `foldLeft`, ale bez iniciálního prvku (iniciální hodnota je první prvek neprázdné kolekce a postupuje se od druhého prvku)
`xs.grouped(size)`                        | Rozdělí kolekci na stejně velké úseky a vrátí je v iterátoru
`xs.sliding(size, step)`                  | Iterátor s pevným sliding window s daným krokem k prvkům kolekce



## Složitosti kolekcí
immutable | head | tail | apply | update | prepend | append | insert
----------|------|------|-------|--------|---------|--------|-------
List      | C    | C    | L     | L      | C       | L      | -
Stream    | C    | C    | L     | L      | C       | L      | -
Vector    | eC   | eC   | eC    | eC     | eC      | eC     | -
Stack     | C    | C    | L     | L      | C       | L      | -
Queue     | aC   | aC   | L     | L      | L       | C      | -
Range     | C    | C    | C     | -      | -       | -      | -
String    | C    | L    | C     | L      | L       | L      | -


mutable       | head | tail | apply | update | prepend | append | insert
--------------|------|------|-------|--------|---------|--------|-------
ArrayBuffer   | C    | L    | C     | C      | L       | aC     | L
ListBuffer    | C    | L    | L     | L      | C       | C      | L
StringBuilder | C    | L    | C     | C      | L       | aC     | L
MutableList   | C    | L    | L     | L      | C       | C      | L
Queue         | C    | L    | L     | L      | C       | C      | L
ArraySeq      | C    | L    | C     | C      | -       | -      | -
Stack         | C    | L    | L     | L      | C       | L      | L
ArrayStack    | C    | L    | C     | C      | aC      | L      | L
Array         | C    | L    | C     | C      | -       | -      | -


immutable       | lookup | add | remove | min
----------------|--------|-----|--------|-----
HashSet/HashMap | eC     | eC  | eC     | L
TreeSet/TreeMap | Log    | Log | Log    | Log
BitSet          | C      | L   | L      | eC
ListMap         | L      | L   | L      | L

---

mutable         | lookup | add | remove | min
----------------|--------|-----|--------|----
HashSet/HashMap | eC     | eC  | eC     | L
WeakHashMap     | eC     | eC  | eC     | L
BitSet          | C      | aC  | C      | eC



## Views
- Transformační operace na kolekcích vytváří vždy novou kolekci (strict transformation)
- Na kolekcích lze vytvořit “pohled”, který aplikuje transformace až při přístupu k prvkům (lazy transformation)
  ```scala
  val view = coll.view.map(_ + 1)
  view(1) // aplikuje map jen na jeden prvek
  ```
  - View lze materializovat zpět do kolekce
    ```scala
    val coll = view.force // aplikuje na všechny prvky
    ```



## Views
- Jsou implementovány jen pro obecné kolekce
  - Ztrácejí informace o původní kolekci (`force` může vrátit jinou kolekci)
- Lze použít pro update mutable kolekcí
  ```scala
  val arr = (0 to 9).toArray
  arr.view.slice(3, 7).transform(-_)
  ```
- Používání views může mást u operací se side-effecty a nevyplatí se pro příliš malé kolekce
  - Doporučují se jen buď v čistě funkcionálním kódu, kde neexistují side-effecty, nebo pro mutaci kolekcí



## Stream
- Speciální kolekce, která má jen lazy transformace
- Pro sestavení a pattern matching lze použít operátor `#::`
  ```scala
  def toZero(n: Int): Stream[Int] =
    n match {
      case 0 => Stream(0)
      case n if n > 0 => n #:: toZero(n - 1)
      case n if n < 0 => n #:: toZero(n + 1)
    }
  ```
- Může být nekonečná
  ```scala
  def fib(a: BigDecimal, b: BigDecimal): Stream[BigDecimal] =
    a #:: fib(b, a + b)
  ```



## For-comprehensions
- Syntactic sugar pro specifické použití `map`, `flatMap` a `filter` operací
  ```scala
  val x = (1 to 10).flatMap { i =>
    (1 to 10).filter(j => i != j).map { j =>
      i * j
    }
  }
  ```
- Kosmeticky podobné `for`/`foreach` cyklům, funkčně je plně nahrazují a rozšiřují
  ```scala
  val x = for {
    i <- 1 to 10
    j <- 1 to 10 if i != j
  } yield i * j
  ```



## For-comprehensions
- Při vynechání klíčového slova `yield` se místo `map` použije `foreach` a návratový typ bude `Unit`
  ```scala
  for { i <- 1 to 10 } println(i)
  ```
- Lze iterovat přes libovolnou kolekci
  ```scala
  val cities = (for {
    person <- people if person.registered
    address <- person.addresses
  } yield address.city).distinct
  ```
- Typům, které jsou navrženy pro práci s for-comprehensions, říkáme monadické (implementují `map`, `flatMap`)



## Implicitní třídy
- Rozšíření stávajících tříd/traitů o nové metody
  ```scala
  implicit class EnhancedString(str: String) {
    def prefix(prefix: String) = prefix + str
  }
  
  println("World".prefix("Hello "))
  ```
- Musí být definovány v jiném traitu, třídě nebo objektu
    - Pro jejich externí použití se musí naimportovat
- Musí mít pouze jeden parametr, který definuje, kterou třídu/trait rozšiřují
- Ve stejném kontextu nesmí existovat nic (metoda, member, objekt) se stejným názvem

Note:
Extensions (extends AnyVal) = nevytváří nový objekt (optimalizace)



## Scala ekosystém
- SBT – univerzální build tool
- Lift – view-first webový framework s důrazem na bezpečnost
- ScalaTest – unit testing framework s mnoha styly syntaxe
- ScalaCheck – framework pro property-based testing
- Spark – framework pro komplexní práci s big data
- Kafka – robustní distribuovaný messaging systém
- Slick – funkcionální mapování relační databáze
- Spray – high-level DSL pro REST webové služby
- Macroid – framework pro vývoj Andoid aplikací
- a další…
    - viz např. [GitHub:lauris/awesome-scala](https://github.com/lauris/awesome-scala)



## Scala historie
- 1995 – vydána Java 1.0
- 1996 – Martin Odersky a Phil Wadler vydali Pizza – generika, funkce vyšších řádů a pattern matching na Javě
- 1998-9 – Generic Java (kompilátor použit jako stardardní pro Java 1.3 v roce 2000, generika integrována do Javy 5 v roce 2004)
- 2000-2 – Funnel – programování s funkcionálními sítěmi, teoretický základ v join kalkulu - nepraktické
- 2004 – vydána Scala 1.0 (něco mezi GJ a Funnelem) pro Javu and .NET
- 2006 – Scala 2.0 – kompilátor ve Scale
- 2009 – Twitter oznámil změnu z Ruby na Scalu


- 2010 – Scala 2.8 – Collections API
- 2011 – 5-letý výzkumný grant a založení Typesafe (Lightbend v roce 2016)
- 2012 – .NET podpora zrušena
- 2013 – Scala 2.10 – implicits, string interpolace, futures & promises, dynamické třídy, podpora JDK 1.6-1.7, actors, atd.
- 2014 – lambdy a Stream API v Javě 8
- 2015 – Scala.js – Scala kompilátor do JavaScriptu
- 2016 – Scala 2.12 – podpora Javy 8 – funkcionální interoperace, využití nových funkcí JVM



## Scala pro pokročilé
- Typové meze, variance, kvantifikace a pohledy
- Implicitní parametry, konverze a evidence
- Typové manifesty
- Polymorfismus ad-hoc, F-bounded a strukturální typy
- Dynamické třídy
- Paralelizace, paralelní kolekce a actor systém (Akka)
- String interpolátory
- Spark, Lift, Spray, ScalaTest, ScalaCheck, ...



## Děkuji za pozornost
Michal Šenkýř - Sklik vývojář



## Úloha – Vyhledávač duplicitních souborů
- Potřebujeme vyhledat všechny duplicitní soubory v daných lokacích
- Duplicitní soubory poznáme podle stejného hashe souborů (SHA-1)
- Vyhledávání duplicit chceme optimalizovat tak, že nejdříve vyhledáme soubory se stejnou velikostí a teprve poté spočítáme hashe a porovnáváme
- Výsledky vypíšeme tak, že na každém řádku bude jedna množina duplicitních souborů oddělená čárkami



## Úloha 2 – N-ticové pexeso
- Chceme vytvořit hru podobnou Pexesu, s tím rozdílem, že nehledáme jen dvojice, ale n-tice
- Na počátku máme zadánu šířku a výšku hracího pole a délku hledaných n-tic
- Vytvoříme náhodné hrací pole a v každém tahu získáváme od hráče koordináty, přičemž mu zobrazujeme zakryté hrací pole s odkrytými v daném tahu vybranými prvky
- Jakmile hráč vybere poslední prvek n-tice, vyhodnotíme výběr a pokud byly vybrány stejné prvky, odstraníme je
- Takto pokračujeme, dokud není pole prázdné. Jakmile je prázdné, hru ukončíme a vypíšeme počet uskutečněných tahů