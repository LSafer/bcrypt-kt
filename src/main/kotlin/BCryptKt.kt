package net.lsafer.bcrypt

import at.favre.lib.crypto.bcrypt.BCrypt

object BCryptKt {
    /**
     * Verify given bcrypt [hash], which includes salt
     * and cost factor with given raw [password].
     *
     * @param password to compare against the hash.
     * @param hash to compare against the password.
     * @param strict true, to also require the version
     *               identifier to match, matching
     *               hash does not suffice.
     * @param verifier the bcrypt verifier to be used.
     * @return true, If [password] matches [hash].
     * @since 1.0.0
     */
    fun verify(
        password: String,
        hash: String,
        strict: Boolean = false,
        verifier: BCrypt.Verifyer = BCrypt.verifyer()
    ): Boolean {
        val passwordBytes = password.toByteArray()
        val hashBytes = hash.toByteArray()
        val result = if (strict)
            verifier.verifyStrict(passwordBytes, hashBytes)
        else
            verifier.verify(passwordBytes, hashBytes)

        return result.verified
    }

    /**
     * Hashes given password with the OpenBSD bcrypt
     * schema. The cost factor will define how
     * expensive the hash will be to generate.
     * This method will use a {@link SecureRandom} to
     * generate the internal 16 byte hash.
     *
     * This implementation will add a null-terminator
     * to the password and return a 23 byte length
     * hash in accordance with the OpenBSD
     * implementation.
     *
     * The random salt will be created internally with
     * a {@link SecureRandom} instance.
     *
     * The given [cost] should be an exponential cost
     * (log2 factor) between [BCrypt.MIN_COST] and
     * [BCrypt.MAX_COST]
     * e.g. 12 --&gt; 2^12 = 4,096 iterations
     *
     * @param password the password to be hashed.
     * @param cost the cost.
     * @param hasher the hasher to be used.
     * @return the hashed password.
     * @since 1.0.0
     */
    fun hash(
        password: String,
        cost: Int = 6,
        hasher: BCrypt.Hasher = BCrypt.withDefaults()
    ): String {
        val passwordBytes = password.toByteArray()
        val result = hasher.hash(cost, passwordBytes)

        return String(result)
    }
}
