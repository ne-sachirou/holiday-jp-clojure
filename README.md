# holiday-jp

![Test](https://github.com/ne-sachirou/holiday-jp-clojure/workflows/Test/badge.svg)

Japanese holiday.

This is an Clojure port of [holiday-jp/holiday_jp-ruby](https://github.com/holiday-jp/holiday_jp-ruby), using holiday data of [holiday-jp/holiday_jp](https://github.com/holiday-jp/holiday_jp).

We support Clojure, Clojerl, Clojure CLR and ClojureScript.

## Installation

### Clojure, ClojureScript

Add to `deps.edn`.

```clojure
{:deps {…
        holiday-jp {:git/url "https://github.com/ne-sachirou/holiday-jp-clojure.git"
                    :sha "〜"}}}
```

### Clojerl

Add to `rebar.config`.

```erlang
{deps, […,
        {holiday-jp, {git, "https://github.com/ne-sachirou/holiday-jp-clojure.git", {branch, "main"}}}]}
```

### Clojure CLR

## Usage

`between` returns a vector of holidays between the start and the last.

```clojure
(is (= [{:date #inst "2021-01-01T00:00:00.000-00:00"
         :week "金"
         :week_en "Friday"
         :name "元日"
         :name_en "New Year's Day"}]
       (holiday-jp/between #inst"2020-12-31" #inst"2021-01-02")))
```

`holiday?` detects the date is a holiday or not.

```clojure
(is (holiday-jp/holiday? #inst"2021-01-01"))

(is (not (holiday-jp/holiday? #inst"2021-01-02")))
```

`on` returns a vector of holidays in the date.

```clojure
(is (= [{:date #inst "2021-01-01T00:00:00.000-00:00"
         :week "金"
         :week_en "Friday"
         :name "元日"
         :name_en "New Year's Day"}]
       (holiday-jp/on #inst"2021-01-01")))
```
