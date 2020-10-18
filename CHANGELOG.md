# 0.3.1

- Fixed an issue where items taken from Clothesline Anchors by automation blocks could be duplicated in some situations.

# 0.3.0

- API changes:
    - BREAKING: Removed the restriction of maximum network momentum. The hand cranking interaction instead inherits the former restriction, while third-party interactions must apply their own restrictions (if any).
    - Changed Nullable annotations from jsr305 to Jetbrains annotations.

# 0.2.1

- Updated to Minecraft 1.16.2.

# 0.2.0

- Updated to Minecraft 1.16.1.
- Changed the mod ID from "clothesline-fabric" to "clothesline". Worlds using pre-0.2.0 versions of Clothesline will be incompatible with 0.2.0 and later versions of Clothesline.
- API additions:
    - Added a client API for rich block interactions as seen on Clothesline Anchors with Cranks.
- Breaking API changes:
    - Moved the API from the `com.jamieswhiteshirt.clotheslinefabric.api` package to the `com.jamieswhiteshirt.clothesline.api` package.
    - Changed the return values of `NetworkState` methods with tickDelta parameters from double to float.
    

# 0.1.2

- Fixed items on clothesline networks rendering with incorrect lighting

# 0.1.1

- Fixed clothesline networks not unloading properly in unloaded chunks
- Fixed closed captions for clothesline running

# 0.1.0

- Updated to Minecraft 1.14.3
- Added rounding of numbers displayed in debug info
- Disabled rendering of debug text that is more than 10 blocks away
- API: Fixed docs in NetworkState
- API (BREAKING): Updated to rtree-3i-lite-fabric 0.3.0
- API (BREAKING): Moved MathUtil out of API

# 0.0.16

- Switched to rtree-3i-lite-fabric as an included dependency instead of shading rtree-3i-lite

# 0.0.15

- Fixed Clotheslines rendering in hand when not active
- Made it possible to place Clothesline Anchors on fences as intended

# 0.0.14

- Updated to Minecraft 1.14 Pre-Release 5 - [#4](https://github.com/JamiesWhiteShirt/clothesline-fabric/pull/4) by asiekierka
- Fixed Clothesline rendering in the wrong hand

# 0.0.13.0

- Updated to Minecraft 19w13b

# 0.0.12.0

- Updated to Minecraft 19w12a

# 0.0.11.0

- Updated to Minecraft 19w11b
- Fixed incompatibility with More Berries causing crashes

# 0.0.10.0

- Updated to Minecraft 19w09a

# 0.0.9.0

- Updated to Minecraft 19w08b
- Moved custom sound events from minecraft to clothesline-fabric namespace

# 0.0.8.0

- Updated to Minecraft 19w07a

# 0.0.7.0

- Updated to Minecraft 19w06a
- Fixed bug disallowing placement of blocks without collision boxes

# 0.0.6.0

- Updated to Minecraft 19w05a
- Fixed bug allowing blocks to be placed in a colliding state

# 0.0.5.0

- Updated to Minecraft 19w04a

# 0.0.4.0

- Updated to Minecraft 19w03b

# 0.0.3.1

- Made clothesline anchors waterloggable
- Fixed bug where clothesline anchors were not lit properly
- Fixed bug where usage interactions for all items except Clotheslines would never end

# 0.0.3.0

- Updated to Minecraft 19w03a
- Removed Clothesline Anchor block entities. Any previously placed cranks will be lost. Sorry!

# 0.0.2.0

- Updated to Minecraft 19w02a
- Fixed bug where Cranks would not drop from Clothesline Anchors
- Fixed bug where Clothesline Anchors would be unable to rotate when their last Clothesline connection is broken

# 0.0.1.0

- Initial release
