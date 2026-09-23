# f(x)

A Mindustry mod.

## Installation

1. Go to the [releases page](https://github.com/refmaven/fx/releases)
   and download the latest `.jar`.
2. Put it in your Mindustry mods folder, or use the in-game
   **Mods → Import Mod** button.
3. Restart the game.

Check the minimum game version listed in each release.

## Building for Desktop Testing

1. Install JDK **17**.
2. Run `gradlew jar` [1].
3. Your mod jar will be in the `build/libs` directory.

**Note:** This build is intended for desktop testing only.
It will not work on Android.

To build an Android-compatible version, you need the Android SDK.
You can either let GitHub Actions handle this, or set it up
yourself. See the steps below.

## Building through GitHub Actions

This repository is set up with GitHub Actions CI to automatically
build the mod on every commit. This requires a GitHub repository,
for obvious reasons.

To get a jar file that works on every platform:

1. Push your changes to your GitHub repository.
2. Check the **Actions** tab on your repository page.
   Select the most recent commit in the list.
3. If the workflow completed successfully, there should be
   a download link under the **Artifacts** section.
4. Click the download link. It should be the name of your repository.
5. Import the jar contained within into Mindustry.

This version should work on both Android and desktop.

## Building Locally

Building locally takes more time to set up, but should not be
a problem if you have done Android development before.

1. Download the Android SDK, unzip it, and set the `ANDROID_HOME`
   environment variable to its location.
2. Make sure you have API level 30 installed, as well as a recent
   version of the build tools, such as `30.0.1`.
3. Add a build-tools folder to your `PATH`. For example, if you
   have `30.0.1` installed, that would be:

   `$ANDROID_HOME/build-tools/30.0.1`

4. Run `gradlew deploy`.

If everything is configured correctly, this will create a jar file
in the `build/libs` directory that can run on both Android
and desktop.

## Source Code

f(x) is free software, licensed under the GNU General Public
License version 3 or any later version.

The complete corresponding source code, including build scripts
and other files needed to build the mod, is available in this
repository.

Each released `.jar` can be matched to its source by checking
out the corresponding release tag.

If you distribute a modified version of f(x), you must comply
with the GNU GPLv3, including providing the corresponding source
code under the applicable license terms.

## Contributing

Contributions are welcome!

By submitting a contribution, such as a pull request or patch,
you agree that your contribution may be distributed under the
GNU General Public License version 3 or any later version,
consistent with the licensing of the rest of the project.

New source files should include a short license notice near
the top of the file:

```java
/*
 * f(x): A Mindustry mod.
 * Copyright (C) 2026 refmaven
 *
 * Licensed under the GNU GPLv3 or later.
 * See the LICENSE file in the project root for the full license text.
 */
```

When distributing modified versions of code derived from Mindustry,
ensure that prominent notices identify the modifications and give
a relevant date.

Original copyright and license notices in adapted code must be preserved.

As a project convention, modified source files should also include
a short note near the top describing the changes and when they were made.

## License

f(x) is free software, licensed under the GNU General Public
License version 3 or any later version.

Copyright (C) 2026 refmaven.

The full license text is available in the
[`LICENSE`](LICENSE) file and at:

<https://www.gnu.org/licenses/gpl-3.0.html>

Unless otherwise stated, source code in this repository is
licensed under the GNU GPLv3 or later.

Individual assets or third-party files may have separate
applicable license terms. Where applicable, those terms are
identified in their respective notices.

## Third-Party Notices

### Mindustry

f(x) incorporates and adapts code from Mindustry.

Mindustry is copyright Anuken and its contributors.

Mindustry is licensed under the GNU General Public License version 3.

Source:

<https://github.com/Anuken/Mindustry>

### Mindustry Java Mod Template

This project was initially based on the
[Mindustry Java Mod Template](https://github.com/Anuken/MindustryJavaModTemplate)
by Anuken.

## Modification Notices

When distributing modified versions of code derived from Mindustry,
ensure that prominent notices identify the modifications and give
a relevant date.

Original copyright and license notices in adapted code must be preserved.

## Disclaimer

f(x) is an independent community project and is not affiliated
with or endorsed by Anuken or the Mindustry development team.

---

*[1]* On Windows, use `gradlew` or `gradlew.bat`.
On Linux/macOS, use `./gradlew`.
