
## 3.6.0 (2020-08-03)

### Enhancements
* All properties in the `SharedPreferencesHelper` are now mutable.
* Added static method `SharedPreferencesHelper.setDefaultPreferenceValues(Context)`.

## 3.5.0 (2020-09-02)

### Enhancements
* Annotation processor will generate `SharedPreferencesHelper`, so you don't need to get `SharedPreferences` value like this: `SharedPreferences.get<DataType>(key, defaultValue)`.
Take advantage of using it with dependency injection such as [Dagger 2](https://github.com/google/dagger) and [Koin](https://github.com/InsertKoinIO/koin).
Personally, I would recommend you to use Koin because of its simplicity.

### Breaking Changes
* `PreferenceKeysConfig` now becomes `PreferencesConfig`.
* `PreferencesConfig` no longer supports `stringResName` since it is getting more complex to develop.

## 3.3.0 (2019-11-08)

### Enhancements
* Introducing `IntegerListPreference` for `integer-array` type entry values.
* Added `OnBindTextInputLayoutListener` as replacement for `OnBindEditTextListener`.
* All preference classes are now `open`.

## 3.1.8 (2019-23-04)

### Enhancements
* `SafeRingtone` is now publicly available.

### Fixed
* `SafeRingtone.title` causes `RuntimeException` if storage permission not granted.

## 3.1.7 (2019-23-04)

### Enhancements
* Updated ProGuard rules

### Fixed
* `RingtonePreference` keeps playing sound after dialog was closed.

## 3.1.5 (2019-27-02)

### Enhancements
* Updated dependencies

## 3.1.4 (2019-08-02)

### Enhancements
* Updated dependencies

## 3.1.3 (2019-27-01)

### Fixed
* `ColorPreference` crash on rotation changed

## 3.1.2 (2019-21-01)

### Fixed
* Inflating `preferences.xml` causes `java.lang.ClassCastException: android.content.res.XmlBlock$Parser cannot be cast to java.lang.AutoCloseable`

## 3.1.1 (2019-20-01)

### Enhancements
* Updated dependencies
* Version `3.1.0` and lower are no longer available to download

## 3.1.0 (2019-05-01)

### Enhancements
* Added preference key generator. With this feature, you don't need to save all preference keys into a constant class each time you modify `preferences.xml`

### Fixed
* `ListPreference` does not update summary correctly
* `FolderPreference` now maintains current path on orientation change

## 3.0.1 (2019-01-01)

### Enhancements
* Migrated to Kotlin
* New `ColorPreference`
* `FolderPreference` now supports API level 19 and lower

### Fixed
* Cannot extends from `PreferenceActivityMaterial` and `PreferenceFragmentMaterial`

## 2.1.0 (2018-12-16)

### Enhancements
* Now you can donate to this library

### Fixed
* Added proguard rules for `DatePreference` and `TimePreference`

## 2.0.0 (2018-12-18)
Version 2.0.0 contains breaking changes that migrates Support Library to AndroidX Jetpack. In the future, version 3 of this library will be written in Kotlin.

### Enhancements
* Added `FolderPreference`
* Added `DatePreference`
* Added `TimePreference`

### Fixed
* None

## 1.0.0 (2018-12-16)

### Enhancements
* Stable release

### Fixed
* None
