package com.peknight.financial

import cats.Applicative
import com.peknight.codec.Codec
import com.peknight.codec.config.given
import com.peknight.codec.derivation.EnumCodecDerivation

enum Offset:
  case Open, Close
end Offset
object Offset:
  given stringCodecOffset[F[_]: Applicative]: Codec[F, String, String, Offset] =
    EnumCodecDerivation.unsafeDerivedStringCodecEnum[F, Offset]
end Offset