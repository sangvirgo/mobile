# Android Project Instructions

## Project Context
- Package: com.sangptit.fragments
- Min SDK: 26, Target SDK: 34
- Language: Java
- Architecture: MVC with Fragments
- IDE: Android Studio

## Coding Rules
- Always use ViewBinding over findViewById
- Fragment communication via Interface pattern (NOT direct Activity cast)
- Follow Material Design 3 guidelines
- Add null checks before all fragment operations
- Use getSupportFragmentManager() for all fragment transactions
- Do NOT ask questions — implement directly based on instructions below

---

## LAB PART 1 — Fragment Communication (Project: Fragments)

### Setup
- Create 2 blank fragments: FragmentA, FragmentB
- Place both in activity_main.xml using FragmentContainerView

### activity_main.xml
- Root: LinearLayout, vertical orientation
- FragmentContainerView for FragmentA:
    - id: @+id/fragmentA
    - name: com.sangptit.fragments.FragmentA
    - layout_weight: 1, layout_width: match_parent, layout_height: wrap_content
- FragmentContainerView for FragmentB:
    - id: @+id/fragmentB
    - name: com.sangptit.fragments.FragmentB
    - layout_width: match_parent, layout_weight: 1, layout_height: wrap_content

### fragment_a.xml
- Root: LinearLayout
- android:orientation="vertical"
- android:background="#038493"
- android:id="@+id/fragmentA"
- android:layout_width="match_parent"
- android:layout_height="match_parent"
- Child: Button
    - android:id="@+id/button"
    - android:layout_width="match_parent"
    - android:layout_height="wrap_content"
    - android:layout_marginTop="50dp"
    - android:layout_marginHorizontal="30dp"
    - android:text="Click Me"

### fragment_b.xml
- Root: FrameLayout
- android:id="@+id/fragmentB"
- android:layout_width="match_parent"
- android:layout_height="match_parent"
- android:background="#99ff66"
- tools:context=".FragmentB"
- Child: TextView
    - android:layout_width="match_parent"
    - android:layout_height="wrap_content"
    - android:layout_margin="30dp"
    - android:textSize="30sp"
    - android:id="@+id/textView"
    - android:layout_gravity="center_horizontal"
    - android:textAlignment="center"
    - android:text="Count: 0"

### FragmentA.java
- Fields: Button button; int count = 0; FragmentB.Counter counter;
- In onCreateView: inflate fragment_a, find button by id
- button.setOnClickListener:
    - count++
    - counter = (FragmentB.Counter) getActivity()
    - counter.incrementValue(count)
- Return view

### FragmentB.java
- Field: TextView textView
- In onCreateView: inflate fragment_b, find textView by id
- Define inner interface:
  public interface Counter {
  public void incrementValue(int count);
  }
- Define method:
  public void setTheCount(int count) {
  textView.setText("Count: " + count);
  }

### MainActivity.java
- extends AppCompatActivity implements FragmentB.Counter
- Override incrementValue(int count):
    - FragmentB fragmentB = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragmentB)
    - if (fragmentB != null) fragmentB.setTheCount(count)

### Communication Flow
FragmentA (button click) → counter.incrementValue(count)
→ MainActivity.incrementValue(count)
→ fragmentB.setTheCount(count)
→ TextView updates

---

## LAB PART 2 — ViewPager + BottomNavigation (Project: ViewPagerNNavigation)

### Project Structure
- Classes: MainActivity, FirstFragment, SecondFragment, ThirdFragment, ViewpagerAdater
- Package: (same base package)

### activity_main.xml
- Root: RelativeLayout
- android:theme="@style/Base.Theme.ViewPagerNNavigation"
- Child 1: androidx.viewpager.widget.ViewPager
    - android:id="@+id/view_pager"
    - android:layout_width="match_parent"
    - android:layout_height="match_parent"
    - android:layout_above="@id/bottom_navigation"
- Child 2: com.google.android.material.bottomnavigation.BottomNavigationView
    - android:id="@+id/bottom_navigation"
    - android:layout_width="match_parent"
    - android:layout_height="wrap_content"
    - android:background="#009688"
    - android:layout_alignParentBottom="true"
    - app:itemTextColor="@color/color_navigation"
    - app:itemIconTint="@color/color_navigation"
    - app:labelVisibilityMode="labeled"
    - app:menu="@menu/menu_navigation"

### res/color/color_navigation.xml
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/white" android:state_checked="false" />
    <item android:color="@color/yellow" android:state_checked="true"/>
</selector>
```

### res/menu/menu_navigation.xml
- 3 items:
    - id: @+id/person, title: "Person", icon: @drawable/person_white
    - id: @+id/home,   title: "Home",   icon: @drawable/home_white
    - id: @+id/settings, title: "Settings", icon: @drawable/settings_white

### Required Drawable Assets (DO NOT CREATE — tell user to add manually)
- person_white.png (or vector XML) — 24dp icon
- home_white.png (or vector XML) — 24dp icon
- settings_white.png (or vector XML) — 24dp icon

### ViewpagerAdater.java
- extends FragmentStatePagerAdapter
- Constructor: (FragmentManager fm, int behavior)
- getItem(int position): switch(position) { case 0: return new FirstFragment(); case 1: return new SecondFragment(); case 2: return new ThirdFragment(); } return null;
- getCount(): return 3;

### MainActivity.java
- Fields: ViewPager mViewPager; BottomNavigationView mBottomNavigationView;
- onCreate:
    - find views by id
    - create ViewpagerAdater with getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    - mViewPager.setAdapter(viewpagerAdater)
    - mViewPager.setCurrentItem(0)
    - mViewPager.addOnPageChangeListener with onPageSelected switching BottomNav checked item
    - mBottomNavigationView.setOnNavigationItemSelectedListener switching mViewPager.setCurrentItem

### gradle.properties — REQUIRED FIX
Add this line to fix switch-case with R.id:
android.nonFinalResIds=false

---

## Prompt Templates (use these to instruct Copilot)

For Part 1:
"Implement Part 1 fragment communication. Start with fragment_a.xml"
"Implement FragmentA.java with Interface pattern per instructions"
"Implement FragmentB.java with Counter interface per instructions"
"Implement MainActivity.java implementing FragmentB.Counter per instructions"

For Part 2:
"Implement Part 2 ViewPager project. Start with activity_main.xml"
"Create ViewpagerAdater.java per instructions"
"Complete MainActivity.java for ViewPager and BottomNavigation per instructions"
"List drawable files I need to add to res/drawable manually"