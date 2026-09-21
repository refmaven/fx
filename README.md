# f(x)

A Mindustry mod.

## Installation

1. Go to the [releases page](https://github.com/[YOUR-USERNAME]/[YOUR-REPO]/releases) and download the latest `.jar`.
2. Put it in your Mindustry mods folder, or use the in-game **Mods → Import Mod** button.
3. Restart the game.

Check the minimum game version listed in each release.

## Building for Desktop Testing

1. Install JDK **17**.
2. Run `gradlew jar` [1].
3. Your mod jar will be in the `build/libs` directory. **Only use this version for testing on desktop. It will not work with Android.**

To build an Android-compatible version, you need the Android SDK. You can either let GitHub Actions handle this, or set it up yourself. See steps below.

## Building through GitHub Actions

This repository is set up with GitHub Actions CI to automatically build the mod for you every commit. This requires a GitHub repository, for obvious reasons.

To get a jar file that works for every platform, do the following:

1. Push your changes to your GitHub repository.
2. Check the "Actions" tab on your repository page. Select the most recent commit in the list. If it completed successfully, there should be a download link under the "Artifacts" section.
3. Click the download link (should be the name of your repo). Import the jar contained within in Mindustry. This version should work both on Android and Desktop.

## Building Locally

Building locally takes more time to set up, but shouldn't be a problem if you've done Android development before.

1. Download the Android SDK, unzip it and set the `ANDROID_HOME` environment variable to its location.
2. Make sure you have API level 30 installed, as well as any recent version of build tools (e.g. 30.0.1).
3. Add a build-tools folder to your PATH. For example, if you have `30.0.1` installed, that would be `$ANDROID_HOME/build-tools/30.0.1`.
4. Run `gradlew deploy`. If you did everything correctly, this will create a jar file in the `build/libs` directory that can be run on both Android and desktop.

## Source Code

f(x) is free software. The complete corresponding source code, including build scripts, is available in this repository. Every released `.jar` can be matched to its source by checking out the corresponding release tag.

If you distribute a modified version of this mod, you must also make your modified source available under the same license (see below).

## Contributing

By submitting a contribution (pull request, patch, etc.), you agree that it will be licensed under the GNU General Public License v3.0 or later, the same as the rest of the project.

New source files should begin with this header:

```java
/*
 * f(x): A Mindustry mod.
 * Copyright (C) 2026 rhxwl
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
```

If you modify a file, add a short note near the top saying what you changed and when.

## License

f(x): A Mindustry mod.
Copyright (C) 2026 rhxwl

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

The full license text is in the [`LICENSE`](LICENSE) file, and is also available at <https://www.gnu.org/licenses/gpl-3.0.html>.

Source code (`src/**`) and assets (`assets/**`) are covered by this license unless a file header says otherwise.

## Third-Party Notices

- **Mindustry** and **Arc** are licensed under the GNU General Public License v3.0. Copyright belongs to their respective authors (Anuken and contributors). Any code or assets copied from them keep their original copyright notices.
- This project was started from the [Mindustry Java Mod Template](https://github.com/Anuken/MindustryJavaModTemplate) by Anuken.

---

*[1]* *On Linux/Mac it's `./gradlew`, but if you're using Linux I assume you know how to run executables properly anyway.*
