# DeliciousCompass

맛있는 나침반 - NaverMap, Naver Open API, BottomSheetBehavior, Moshi

## 1. NaverMap

> 네이버 지도 SDK는 네이버 지도 앱을 비롯한 네이버의 여러 서비스에서 사용 중인 엔진입니다. 대규모 사용자가 이용하는 서비스에 다년간 적용되어 기능과 안정성이 보장됩니다.
> 또한 개발자 친화적인 API를 제공하므로 SDK가 제공하는 강력한 기능을 쉽게 사용할 수 있습니다.
>
> 네이버 지도 SDK는 네이버 지도같은 복잡한 서비스에서 요구되는 다양한 기능을 모두 수용하는 강력한 기능을 제공합니다.
> [네이버 지도 SDK 소개](https://navermaps.github.io/android-map-sdk/guide-ko/0.html)

## 2. Naver Open API

> 네이버 오픈API는 네이버 플랫폼의 기능을 외부 개발자가 쉽게 이용할 수 있게 웹 또는 SDK 형태로 공개한 기술들입니다. 네이버 오픈API로 활용할 수 있는 기술에는 네이버
> 로그인과 지도, 검색이 있으며, Clova의 음성 인식 기술과 음성 합성 기술, 얼굴 인식 기술, Papago의 기계 번역 기술 등이 있습니다.
> [네이버 Open API 공통 가이드](https://developers.naver.com/docs/common/openapiguide/)

### 비로그인 방식 오픈 API - 지역 검색 개요

https://developers.naver.com/docs/serviceapi/search/local/local.md#%EC%A7%80%EC%97%AD

검색 API와 지역 검색 개요

> 검색 API는 네이버 검색 결과를 뉴스, 백과사전, 블로그, 쇼핑, 영화, 웹 문서, 전문정보, 지식iN, 책, 카페글 등 분야별로 볼 수 있는 API입니다. 그 외에 지역
> 검색 결과와 성인 검색어 판별 기능, 오타 변환 기능을 제공합니다.
>
> 지역 검색은 검색 API를 사용해 네이버 지역 서비스에 등록된 업체 및 기관을 검색한 결과를 반환하는 RESTful API입니다. 지역 검색 결과를 XML 형식 또는 JSON
> 형식으로 반환합니다. API를 호출할 때는 검색어와 검색 조건을 쿼리 스트링(Query String) 형식의 데이터로 전달합니다.
>
> 지역 검색은 검색 API를 사용하며, 검색 API의 하루 호출 한도는 25,000회입니다.

검색 API 특징
> 검색 API는 비로그인 방식 오픈 API입니다.
>
> 비로그인 방식 오픈 API는 네이버 오픈API를 호출할 때 HTTP 요청 헤더에 클라이언트 아이디와 클라이언트 시크릿 값만 전송해 사용할 수 있는 오픈 API입니다. 클라이언트
> 아이디와 클라이언트 시크릿은 네이버 오픈API에서 인증된 사용자인지 확인하는 수단입니다. 네이버 개발자 센터에서 애플리케이션을 등록하면 클라이언트 아이디와 클라이언트 시크릿이
> 발급됩니다.
>
> **참고**
>
> 네이버 오픈API의 종류와 클라이언트 아이디, 클라이언트 시크릿에 관한 자세한 내용은 "API 공통 가이드"를 참고하십시오.

## 3. BottomSheetBehavior

> An interaction behavior plugin for a child view of CoordinatorLayout to make it work as a bottom
> sheet.
>
> To send useful accessibility events, set a title on bottom sheets that are windows or are
> window-like.
>
> For BottomSheetDialog use `AppCompatDialog.setTitle(int)`, and for
> BottomSheetDialogFragment use `ViewCompat.setAccessibilityPaneTitle(View, CharSequence)`.
> [Android Developer](https://developer.android.com/reference/com/google/android/material/bottomsheet/BottomSheetBehavior)

### Material3

https://github.com/material-components/material-components-android/blob/master/docs/components/BottomSheet.md

### CoordinatorLayout

- FrameLayout 기반의 상호작용 레이아웃
- 자식 Behavior 들과의 이동 및 애니메이션 작용 등을 다룰 때 사용
    - AppBarLayout 의 스크롤 시 크기 변경
    - 하단 FloatingButton 의 스크롤 시 위치 변경 등

## 4. Moshi

- Gson (google)
    - Java 로 구현
    - Gson도 최근에 업데이트 되고 있음
- Moshi (square)
    - Kotlin 으로 구현
    - Null 처리
