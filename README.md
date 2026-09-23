# f(x)

A Mindustry mod.

## Installation

1. Download the latest release from the
   [Releases](https://github.com/refmaven/fx/releases) page.
2. Open Mindustry.
3. Go to **Settings → Mods → Import Mod**.
4. Select the downloaded `.jar` file.

## Building for Desktop Testing

To build the mod and install it directly into the desktop
Mindustry mods directory, run:

```bash
gradlew deploy
```

On Linux/macOS, use:

```bash
./gradlew deploy
```

This task builds the mod and copies it to the appropriate
Mindustry mods directory for local testing.

## Building through GitHub Actions

This repository includes a GitHub Actions workflow for building
the mod automatically.

To build through GitHub Actions:

1. Open the **Actions** tab on GitHub.
2. Select the relevant build workflow.
3. Run the workflow manually, if supported, or push a commit
   that triggers it.

The generated `.jar` file can be downloaded from the workflow's
artifacts.

## Building Locally

To build the mod without deploying it, run:

```bash
gradlew jar
```

On Linux/macOS, use:

```bash
./gradlew jar
```

The compiled `.jar` file will be located in:

```text
build/libs/
```

## Source Code

The source code is available in this repository.

The project is primarily written in Java and uses the
Mindustry modding API.

## Contributing

Contributions are welcome!

By submitting a contribution, such as a pull request or patch,
you agree that your contribution may be distributed under the
GNU General Public License version 3 or any later version,
consistent with the licensing of the rest of the project.

Contributors are not required to add a full license header to
every source file. The project-wide license information is
provided in this README and in the `LICENSE` file.

When contributing code derived from or copied from another
project:

- Preserve applicable copyright and license notices.
- Do not remove or replace existing upstream notices.
- Clearly identify substantial modifications where appropriate.
- Follow the license terms that apply to the original code.

Contributors may use a short notice in source files when useful,
but such a notice is not required for every file in the project.

## License

f(x) is licensed under the
[GNU General Public License version 3 or later](https://www.gnu.org/licenses/gpl-3.0.html).

The full license text is available in the
[`LICENSE`](LICENSE) file and at:

<https://www.gnu.org/licenses/gpl-3.0.html>

Unless otherwise stated, the source code in this repository
is licensed under the GNU GPLv3 or later.

Individual assets or third-party files may have separate
applicable license terms. Where relevant, those terms are
identified in their respective notices.

## Third-Party Notices

### Mindustry

f(x) incorporates and adapts portions of source code from
Mindustry by Anuken and its contributors.

Some of the adapted code has been modified for use in f(x).
Relevant modification period: 2026.

Mindustry is licensed under the
GNU General Public License version 3.

Source:

<https://github.com/Anuken/Mindustry>

When distributing modified portions derived from Mindustry,
the applicable original copyright and license notices must
be preserved.

### Mindustry Java Mod Template

This project was initially based on the
[Mindustry Java Mod Template](https://github.com/Anuken/MindustryJavaModTemplate)
by Anuken.

The template's applicable license terms remain relevant to
the portions of the project derived from it.

## Upstream Code Notices

This repository may contain code copied from, adapted from,
or otherwise derived from upstream projects.

The primary upstream project currently identified in this
repository is Mindustry.

The adapted portions have been modified for use in f(x)
during 2026.

Original copyright and license notices are preserved where
applicable. The applicable upstream license terms continue
to apply to the relevant portions of the code.

This notice describes the project's upstream code generally.
It does not claim that every file in the repository is copied
from or derived from Mindustry.

## Disclaimer

f(x) is an independent community project and is not affiliated
with or endorsed by Anuken or the Mindustry development team.

---

*[1]* On Linux/macOS, use `./gradlew` instead of `gradlew`.
