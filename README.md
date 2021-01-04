# holiday-jp

[![Test](https://github.com/ne-sachirou/holiday-jp-clojure/workflows/Test/badge.svg)](https://github.com/ne-sachirou/holiday-jp-clojure/actions?query=workflow:Test)
[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.ne_sachirou/holiday-jp.svg)](https://clojars.org/org.clojars.ne_sachirou/holiday-jp)
[![Hex.pm](https://img.shields.io/hexpm/v/holiday_jp_clje.svg)](https://hex.pm/packages/holiday_jp_clje)

Japanese holiday.

This is an Clojure port of [holiday-jp/holiday_jp-ruby](https://github.com/holiday-jp/holiday_jp-ruby), using holiday data of [holiday-jp/holiday_jp](https://github.com/holiday-jp/holiday_jp).

We support Clojure, Clojerl, Clojure CLR and ClojureScript.

## LICENSE

GPL-3.0-or-later

## Installation

### Clojure, ClojureScript

Add to `deps.edn`.

```clojure
{:deps {…
        org.clojars.ne_sachirou/holiday-jp-clj {:mvn/version "0.1.0"}}}
```

### Clojerl

Add to `rebar.config`.

```erlang
{deps, […,
        {holiday_jp, "~> 0.1.0", {pkg, holiday_jp_clje}}]}
```

### Clojure CLR

## Usage

`between` returns a vector of holidays between the start and the last.

```clojure
(is (= [{:date #inst "2021-01-01T00:00:00.000+09:00"
         :week "金"
         :week_en "Friday"
         :name "元日"
         :name_en "New Year's Day"}]
       (holiday-jp/between #inst"2020-12-31T00:00:00+09:00" #inst"2021-01-02T00:00:00+09:00")))
```

`holiday?` detects the date is a holiday or not.

```clojure
(is (holiday-jp/holiday? #inst"2021-01-01T00:00:00+09:00"))

(is (not (holiday-jp/holiday? #inst"2021-01-02T00:00:00+09:00")))
```

`on` returns a vector of holidays in the date.

```clojure
(is (= [{:date #inst "2021-01-01T00:00:00.000+09:00"
         :week "金"
         :week_en "Friday"
         :name "元日"
         :name_en "New Year's Day"}]
       (holiday-jp/on #inst"2021-01-01T00:00:00+09:00")))
```
